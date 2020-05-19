DELETE FROM user__2018;

INSERT INTO user__2018 (id, name, age, email, grade, gender, state,version) VALUES
(2, 'Jack', 3, 'test2@baomidou.com', 1, 0, 1,1),
(3, 'Tom', 1, 'test3@baomidou.com', 2, 1, 1,1),
(1, 'Billie', 2, 'test5@baomidou.com', 3, null,1,1);

DELETE FROM user__2019;
INSERT INTO user__2019 (id, name, age, email, grade, gender, state,version) VALUES
(2, 'Jack', 3, 'test2@baomidou.com', 1, 0, 1,1),
(3, 'Tom', 1, 'test3@baomidou.com', 2, 1, 1,1),
(1, 'Billie', 2, 'test5@baomidou.com', 3, null,1,1);


DELETE FROM man;
insert into man(id, name, lao_po_id)
values (1, '程序猿小明', 1),
       (2, '隔壁老王', 2);

DELETE FROM woman;
insert into woman(id, name, lao_gong_id)
values (1, '程序猿小明老婆', 1),
       (2, '隔壁老王老婆', 2);

DELETE FROM child;
INSERT INTO child (id, name, lao_han_id, lao_ma_id)
VALUES (1, '小小明', 1, 1),
       (2, '小小王', 2, 2),
       (3, '旺仔', 2, 1),
       (4, '小馒头', 2, 1),
       (5, '大礼包', 1, 2);