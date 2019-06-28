drop table if exists `t_user`;
create table `t_user` (
  `t_id` int(11) not null auto_increment comment '编号',
  `t_name` varchar(30) default null comment '名称',
  `t_age` int(10) default null comment '年龄',
  `t_address` varchar(100) default null comment '家庭住址',
  primary key (`t_id`)
)
