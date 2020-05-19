package com.bolly.mybatisplus;

import com.bolly.mybatisplus.entity.Child;
import com.bolly.mybatisplus.entity.Man;
import com.bolly.mybatisplus.entity.Woman;
import com.bolly.mybatisplus.mapper.ChildMapper;
import com.bolly.mybatisplus.mapper.ManMapper;
import com.bolly.mybatisplus.mapper.WomanMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author miemie
 * @since 2019-11-27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultmapTest {

    @Resource
    private ChildMapper childMapper;
    @Resource
    private ManMapper manMapper;
    @Resource
    private WomanMapper womanMapper;

    @Test
    public void t_c() {
        final Child child = childMapper.selectLinkById(1L);
        log.info("child: {}", child);
        assertThat(child).isNotNull();
        final Man laoHan = child.getLaoHan();
        assertThat(laoHan).isNotNull();
        assertThat(laoHan.getName()).isNotBlank();
        final Woman laoMa = child.getLaoMa();
        assertThat(laoMa).isNotNull();
        assertThat(laoMa.getName()).isNotBlank();
    }

    @Test
    public void t_m() {
        final Man man = manMapper.selectLinkById(1L);
        log.info("man: {}", man);
        assertThat(man).isNotNull();
        assertThat(man.getName()).isNotBlank();
        final Woman laoPo = man.getLaoPo();
        assertThat(laoPo).isNotNull();
        assertThat(laoPo.getName()).isNotBlank();
        final List<Child> waWa = man.getWaWa();
        assertThat(waWa).isNotEmpty();
        waWa.forEach(i -> assertThat(i.getName()).isNotBlank());
    }

    @Test
    public void t_w() {
        final Woman woman = womanMapper.selectLinkById(1L);
        log.info("woman: {}", woman);
        assertThat(woman).isNotNull();
        assertThat(woman.getName()).isNotBlank();
        final Man laoGong = woman.getLaoGong();
        assertThat(laoGong).isNotNull();
        assertThat(laoGong.getName()).isNotBlank();
        final List<Child> waWa = woman.getWaWa();
        assertThat(waWa).isNotEmpty();
        waWa.forEach(i -> assertThat(i.getName()).isNotBlank());
    }
}
