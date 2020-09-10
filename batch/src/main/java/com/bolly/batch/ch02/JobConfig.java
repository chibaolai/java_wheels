package com.bolly.batch.ch02;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<CreditBill> reader1(@Value("#{jobParameters['input.file.name']}") String pathToFile) {
        FlatFileItemReader<CreditBill> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource(pathToFile));
        itemReader.setLineMapper(new DefaultLineMapper<CreditBill>(){{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(",");
                setNames(new String[] {"accountId","name","amount","date","address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditBill>(){{
                setTargetType(CreditBill.class);
            }});
        }});
        return itemReader;
    }

    @Bean
    public ItemProcessor<CreditBill, CreditBill> processor1() {
        return new CreditBillProcessor();
    }


    @Bean
    public ItemWriter<CreditBill> writer1() {
        FlatFileItemWriter<CreditBill> flatFileItemWriter = new FlatFileItemWriter();
        flatFileItemWriter.setResource(new FileSystemResource("out/ch02/outputFile.csv"));
        flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator() {{
            setFieldExtractor(new BeanWrapperFieldExtractor(){{
                String[] list = {"accountId","name","amount","date","address"};
                setNames(list);
            }});
            setDelimiter(",");
        }});
        return flatFileItemWriter;
    }

    @Bean
    public Step creditStep(ItemReader<CreditBill> reader, ItemWriter<CreditBill> writer,
                           ItemProcessor<CreditBill,CreditBill> processor) {
        return stepBuilderFactory.get("creditStep").transactionManager(new ResourcelessTransactionManager()).<CreditBill,CreditBill>chunk(1000).reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    public Job billJob(Step creditStep) {
        return jobBuilderFactory.get("billJob").incrementer(new RunIdIncrementer()).flow(creditStep).end().build();
    }
}
