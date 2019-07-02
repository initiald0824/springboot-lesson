drop table if exists `t_user`;
create table `t_user` (
  `t_id` int(11) not null auto_increment comment '编号',
  `t_name` varchar(30) default null comment '名称',
  `t_age` int(10) default null comment '年龄',
  `t_address` varchar(100) default null comment '家庭住址',
  `t_pwd` varchar(100) default null comment '用户密码',
  primary key (`t_id`)
);

drop table if exists `t_logger_infos`;
create table `t_logger_infos` (
  `id` int (11) not null auto_increment,
  `client_ip` varchar(30) default null comment '客户端请求ip',
  `uri` varchar(100) default null comment '日志请求地址',
  `type` varchar(50) default null comment '终端请求方式，普通请求，ajax请求',
  `method` varchar(10)  default null comment '请求方式method, post, get等',
  `param_data` longtext comment '请求参数内容, json',
  `session_id` varchar(100) default null comment '接口返回时间',
  `time` timestamp null default current_timestamp on update current_timestamp comment '请求时间',
  `return_time` varchar(50) default null comment '接口返回时间',
  `return_data` longtext comment '接口返回数据json',
  `http_status_code` varchar(10) default null comment '请求时httpStatusCode代码, 如: 200, 400, 404等',
  `time_consuming` int(8) default '0' comment '请求耗时(毫秒单位)',
  primary key (`id`)
)
