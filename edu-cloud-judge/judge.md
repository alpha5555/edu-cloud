# 判题服务

### 判题

​	1、提交答案

​	2、插入判题记录

​	3、选择题：获取答案判题，更新判题记录，更新用户提交（正确量、提交量），更新题目（正确量、提交量），回传结果

​		  填空题：不做处理

​		  代码题：提交recordId到消息队列，等待消息队列监听

​						 1）获取测试样例

​						 2）生成请求体

​						 3）同步发送判题请求至判题机

​						 4）解析结果

​						 5）更新判题记录，更新用户提交（正确量、提交量），更新题目（正确量、提交量）

​	4、回显（学生：回显判题结果，老师：回显判题结果详情(含测试样例通过详情)）

​	5、教师改判

### 课堂答题记录管理

​	获取课堂答题记录

​	获取课堂答题排名统计

​	获取学生在每个课堂答题情况



### 考试答题记录管理

​	获取当考生的答题记录（含分数）

​	获取考场答题记录

​	一键批改：根据正确与否，和题目对应的分数打分

​	教师改分