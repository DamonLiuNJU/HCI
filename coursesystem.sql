# Host: localhost  (Version: 5.5.32)
# Date: 2013-12-29 17:19:54
# Generator: MySQL-Front 5.3  (Build 2.42)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

#
# Source for table "course"
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(8) NOT NULL,
  `name` varchar(10) NOT NULL,
  `teacherID` varchar(8) NOT NULL,
  `campus` varchar(10) DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `place` varchar(10) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  `require` varchar(30) DEFAULT NULL,
  `facultyID` varchar(8) DEFAULT NULL,
  `credit` int(2) DEFAULT NULL,
  `module` varchar(10) DEFAULT NULL,
  `limit` int(3) NOT NULL,
  `specificInfo` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `facultyID` (`facultyID`),
  KEY `teacherID` (`teacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "course"
#

INSERT INTO `course` VALUES ('c000101','软件工程与计算','2001003','仙林','大一下','仙二304','星期四 5-6&星期二 5-6','1-17','无','001',3,'必修课',-1,'无'),('c000102','数据结构与算法分析','2001002','仙林','大二上','仙二303','星期三 5-6','2-18','无','001',3,'必修课',-1,'无'),('c000104','计算组织与结构','2001004','仙林','大一下','仙二305','星期二 5-7','1-17','无','001',3,'必修课',-1,'无'),('c000105','软件构造','2001001','鼓楼','大三上','教201','星期二 3-4','1-17','无','001',3,'必修课',-1,'无'),('c000106','离散数学','2001001','仙林','大一上','图书馆106','星期二 5-7','1-17','无','001',1,'必修课',18,'无'),('c000107','软件需求工程','2001009','鼓楼','大三上','教202','星期五 3-4','1-17','无','001',3,'必修课',-1,'无\n'),('c001301','数学分析','2013002','仙林','大一下','仙二110','星期二 1-4','1-17','无','013',5,'必修课',-1,'无'),('c100101','c++高级程序设计','2001007','仙林','大二上','仙一303','星期四 3-4','1-17','无','001',3,'选修课',-1,''),('c100102','移动互联网','2001003','鼓楼','大二下','教205','星期三 7-8','1-17','无','001',3,'选修课',18,'无'),('c100103','多媒体技术','2001004','鼓楼','大四上','教106','星期五 7-8','1-17','无','001',3,'选修课',-1,'无'),('c100104','数据库开发技术','2001008','鼓楼','大三上','教233','星期五 1-3','1-17','无','001',3,'选修课',18,'无'),('c200201','山水地质学与中国绘画','2002001','仙林','上','仙一319','星期一 9-11','1-2','无','002',3,'通识课',15,'无'),('c200301','变态心理学','2003001','仙林','上','仙一319','星期一 9-10','2-13','无','003',2,'通识课',1,'无'),('c200402','马克思主义','2004001','仙林','上','仙一319','星期一 9-10','1-2','无','004',2,'通识课',40,'无'),('c201001','诗与思：近代德语文学','2010001','仙林','下','仙一210','星期一 6-9','2-13','无','010',2,'通识课',16,'无'),('c202001','中华玉器鉴赏','2020001','仙林','上','仙二122','星期四 7-8','2-17','无','020',2,'通识课',22,'无'),('c221','19世纪法语文学赏析','2001001','仙林','大一上','仙1 405','星期三 6-7','1-11','','001',2,'必修课',30,''),('c300201','西方音乐','2002002','仙林','下','仙一319','星期一 9-10','2-9','无','002',2,'公选课',22,'无'),('c300202','油画绘画技法基础','2002003','仙林','下','仙二404','星期二 5-6','2-16','无','002',3,'公选课',20,'无'),('c401901','体育舞蹈','2019001','仙林','大一上','体育馆主馆','星期四 3-4','1-17','无','019',1,'体育课',30,'无'),('c401902','太极拳32式','2019002','仙林','大一上','体育馆武术馆','星期三 1-2','1-17','无','019',1,'体育课',40,'无'),('c401903','跆拳道','2019003','仙林','下','体育馆跆拳道馆','星期二 5-6','1-17','无','019',1,'体育课',18,'无');

#
# Source for table "courseapplication"
#

DROP TABLE IF EXISTS `courseapplication`;
CREATE TABLE `courseapplication` (
  `name` varchar(20) NOT NULL,
  `teacherID` varchar(8) NOT NULL,
  `facultyID` varchar(8) NOT NULL,
  `applyinfo` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "courseapplication"
#

/*!40000 ALTER TABLE `courseapplication` DISABLE KEYS */;
INSERT INTO `courseapplication` VALUES ('课程','2001001','001','课程名称: 课程\n课时数： 20\n最大学生数： 200\n学分： 5\n期望上课时间： 周一3~4\n教学目的： 。。。\n教学计划： 。。。\n课程模块： 选修课\n'),('唐诗','2002002','002','深入研究20首具有代表意义的唐诗作品，提高大学生基本的传统诗词修养。'),('宋词','2002002','002','深入研究20首具有代表意义的宋词作品，提高大学生基本的传统诗词修养。');
/*!40000 ALTER TABLE `courseapplication` ENABLE KEYS */;

#
# Source for table "coursestatus"
#

DROP TABLE IF EXISTS `coursestatus`;
CREATE TABLE `coursestatus` (
  `module` varchar(10) NOT NULL,
  `type` varchar(15) NOT NULL,
  `on` varchar(10) NOT NULL,
  `off` varchar(10) NOT NULL,
  PRIMARY KEY (`module`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "coursestatus"
#

/*!40000 ALTER TABLE `coursestatus` DISABLE KEYS */;
INSERT INTO `coursestatus` VALUES ('体育课','publish','5-30','12-31'),('体育课','recordscore','6-25','12-31'),('体育课','select','6-10','12-31'),('公选课','publish','5-30','12-31'),('公选课','quit_add','9-2','12-31'),('公选课','recordscore','6-25','12-31'),('公选课','select','6-10','12-31'),('必修课','apply','6-20','12-31'),('必修课','publish','5-20','12-27'),('必修课','recordscore','6-25','12-27'),('选修课','publish','5-25','12-27'),('选修课','quit_add','9-2','12-31'),('选修课','recordscore','6-25','12-31'),('选修课','select','6-20','12-31'),('通识课','publish','5-25','12-31'),('通识课','quit_add','9-2','12-31'),('通识课','recordscore','6-25','12-31'),('通识课','select','12-1','12-31');
/*!40000 ALTER TABLE `coursestatus` ENABLE KEYS */;

#
# Source for table "faculty"
#

DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty` (
  `id` varchar(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `plan` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "faculty"
#

INSERT INTO `faculty` VALUES ('001','软件学院','plantest11'),('002','文学院','文学院教学计划'),('003','社会学院','社会学院教学计划'),('004','政府管理学院','政府管理学院教学计划'),('005','历史学系',''),('006','哲学系',''),('007','新闻传播学院',''),('008','法学院',''),('009','商学院',''),('010','外国语学院',''),('011','信息管理学院',''),('012','海外教育学院',''),('013','数学院',''),('014','物理学院',''),('015','化学化工学院',''),('016','天文与空间科学学院',''),('017','计算机科学与技术学院',''),('018','电子科学与工程学院',''),('019','体育部',''),('020','地理科学与工程学院','');

#
# Source for table "majortransfer"
#

DROP TABLE IF EXISTS `majortransfer`;
CREATE TABLE `majortransfer` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `preschool` varchar(255) DEFAULT NULL,
  `toschool` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `applydate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=121250090 DEFAULT CHARSET=utf8;

#
# Data for table "majortransfer"
#

/*!40000 ALTER TABLE `majortransfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `majortransfer` ENABLE KEYS */;

#
# Source for table "manager"
#

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(8) NOT NULL,
  `password` varchar(12) NOT NULL,
  `name` varchar(5) NOT NULL,
  `facultyID` varchar(5) NOT NULL DEFAULT '-1',
  `contactInfo` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "manager"
#

/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('01','admin','管理员','-1','admin@nju.edu.cn'),('100101','100101','安安','001','aa@nju.edu.cn'),('100102','100102','石头','001','无'),('100201','100201','五月','002','wy@nju.edu.cn'),('100301','100301','小雨','003','xy@nju.edu.cn'),('100401','100401','小鱼','004','xyy@nju.edu.cn'),('100501','100501','五五','005','无'),('100601','100601','刘一','006','ly'),('100701','100601','lucky','007','无'),('100801','100801','八八','008','无'),('100901','100901','九九','009','无'),('101001','101001','十十','010','无'),('101101','101101','大左','011','无'),('101201','101201','张小马','012','无'),('101301','101301','凌零','013','无'),('101401','101401','傅远','014','无'),('101501','101501','贺云','015','无'),('101601','101601','张扬','016','无'),('101701','101701','路露','017','无'),('101801','101801','程青','018','无'),('101901','101901','小任','019','无'),('102001','102001','梁晨','020','无'),('11','1234','图图','-1','tudean@nju.edu.cn'),('12','12345','p菲','-1','无');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;

#
# Source for table "managestatus"
#

DROP TABLE IF EXISTS `managestatus`;
CREATE TABLE `managestatus` (
  `type` varchar(15) NOT NULL,
  `on` varchar(10) NOT NULL,
  `off` varchar(10) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "managestatus"
#

/*!40000 ALTER TABLE `managestatus` DISABLE KEYS */;
INSERT INTO `managestatus` VALUES ('frame','12-5','12-27'),('plan','12-1','12-27');
/*!40000 ALTER TABLE `managestatus` ENABLE KEYS */;

#
# Source for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `index` int(5) NOT NULL AUTO_INCREMENT,
  `from_id` varchar(8) NOT NULL,
  `to_id` varchar(8) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`index`),
  KEY `index1` (`from_id`),
  KEY `index2` (`to_id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

#
# Data for table "message"
#

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'11','100301','社会学院教学计划有偏差，请修改。'),(2,'11','100201','test'),(3,'11','100501','历史系专业课准出安排有偏差，请修改。'),(4,'11','100101','修改建议测试'),(5,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(6,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(7,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(8,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(9,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(10,'11','100201','此门课程申请已被拒绝，课程信息如下：小布--晚宋词,请通知小布老师。'),(11,'11','100101','请及时发布此门课程并通知该教师，课程信息如下：陈道蓄--19世纪法语文学赏析 :\r\n    课程名称: 19世纪法语文学赏析\n课时数： 29\n最大学生数： 30\n学分： 2\n期望上课时间： 周日\n教学目的： 深入理解19世纪法国的文学发展与社会变迁。\n教学计划： 上课10周左右。\n'),(12,'11','100101','请及时发布此门课程并通知该教师，课程信息如下：陈道蓄--19世纪法语文学赏析 :\r\n    ');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

#
# Source for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(10) NOT NULL,
  `password` varchar(12) DEFAULT '',
  `name` varchar(5) DEFAULT '',
  `facultyID` varchar(8) DEFAULT '',
  `entranceyear` varchar(5) DEFAULT '',
  `contactInfo` varchar(30) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `homeaddress` varchar(255) DEFAULT NULL,
  `mothername` varchar(255) DEFAULT NULL,
  `motherphone` varchar(255) DEFAULT NULL,
  `fathername` varchar(255) DEFAULT NULL,
  `fatherphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

INSERT INTO `student` VALUES ('10001001','zhangping','张凭','001','2010','无','无','5A210','张凭妈妈',NULL,'张凭爸爸',NULL),('10001002','shidi','时迪','001','2010','无','15543223433','5A210','时迪妈妈',NULL,'时迪爸爸',NULL),('11001001','qiangxin','强鑫','001','2011','无','13276877761','2B511','强鑫妈妈',NULL,'强鑫爸爸',NULL),('11001002','xueye','薛晔','001','2011','无','13244321234','2A322','薛晔爸爸',NULL,'薛晔妈妈',NULL),('11002001','xiaohua','小花','002','2011','xh@.edu.cn','12001','2B300','flower','','eric',''),('12001001','yy','一一','001','2012','yy@software.nju.edu.cn','10089','5B518','一一妈妈','','一一爸爸',''),('12001002','rr','二二','001','2012','ee@software.nju.edu.cn','10090','5B520','linda','','simon',''),('12001003','liulan','刘澜','001','2012','ll@software.nju.edu.cn','15004181765','2B308','刘澜妈妈','','刘澜爸爸',''),('12001004','liurui','刘睿','001','2012','lr@software.nju.edu.cn','15867990121','5A501','刘睿妈妈',NULL,'刘睿爸爸',NULL),('12001005','liuweiting','刘威廷','001','2012','lwt@software.nju.edu.cn','13222109878','5A501','刘威廷妈妈',NULL,'刘威廷爸爸',NULL),('12001006','zhaochao','赵潮','001','2012','无','15676552333','5B502','乔乔妈妈',NULL,'乔乔爸爸',NULL),('12001007','zhangxiao','张骁','001','2012','无','13564567768','5B211','张骁妈妈',NULL,'张骁爸爸',NULL),('12001008','xinglinzhou','邢麟舟','001','2012','无','15645323451','5B211','老大妈妈',NULL,'老大爸爸',NULL),('12001009','yuqian','于谦','001','2012','无','15963765402','2B513','于谦妈妈',NULL,'于谦爸爸',NULL),('12001010','xujiaxing','许嘉星','001','2012','无','13156476318','5B212','田鼠妈妈',NULL,'田鼠爸爸',NULL),('12001011','hexintong','何欣彤','001','2012','无','15643733345','2B304','蘑菇妈妈',NULL,'蘑菇爸爸',NULL),('12001012','ludi','芦迪','001','2012','无','15644332312','5B211','芦迪妈妈',NULL,'芦迪爸爸',NULL),('12001013','lipeiqi','李沛褀','001','2012','无','13245465788','2B513','皮皮妈妈',NULL,'皮皮爸爸',NULL),('12001014','caojiayuan','曹佳媛','001','2012','无','13162157656','2B306','佳媛妈妈',NULL,'佳媛爸爸',NULL),('12001015','pengyifei','彭逸菲','001','2012','无','15645664321','2B305','p菲妈妈',NULL,'p菲爸爸',NULL),('12001016','liuyue','刘玥','001','2012','无','15867545512','2B310','刘玥妈妈',NULL,'刘玥爸爸',NULL),('12001017','lixue','李学','001','2012','无','15654332216','5B211','李学妈妈',NULL,'李学爸爸',NULL),('12001018','tangyuzi','唐羽姿','001','2012','无','15211651323','2B410','鱼妈妈',NULL,'鱼爸爸',NULL),('12001019','zhangyao','张瑶','001','2012','无','15867665466','2B307','张瑶妈妈',NULL,'张瑶爸爸',NULL),('13001001','zhangyunjia','张韵嘉','001','2013','无','02986712311','2B404','大姐妈妈',NULL,'大姐爸爸',NULL),('13002001','fengchuchu','冯楚楚','002','2013','无','15900908767','2B304','楚楚妈妈',NULL,'楚楚爸爸',NULL),('13002002','fengxiaochu','冯小楚','002','2013','无','15900908768','2B304','图图妈妈',NULL,'图图爸爸',NULL),('13002003','guoruizhe','郭睿哲','002','2013','无','15992090060','2A311','郭郭妈妈',NULL,'郭郭爸爸',NULL);

#
# Source for table "selectcourserecord"
#

DROP TABLE IF EXISTS `selectcourserecord`;
CREATE TABLE `selectcourserecord` (
  `student_id` varchar(8) NOT NULL,
  `course_id` varchar(8) NOT NULL,
  `score` int(3) DEFAULT NULL,
  `stugrade` varchar(20) DEFAULT '',
  KEY `course_id` (`course_id`),
  KEY `index_2` (`student_id`),
  CONSTRAINT `selectcourserecord_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "selectcourserecord"
#

INSERT INTO `selectcourserecord` VALUES ('10001001','c000105',90,'大四上'),('10001001','c401902',0,'大四上');

#
# Source for table "auditinfo"
#

DROP TABLE IF EXISTS `auditinfo`;
CREATE TABLE `auditinfo` (
  `student_id` varchar(8) NOT NULL,
  `type` varchar(10) NOT NULL,
  `specificInfo` varchar(100) NOT NULL,
  `result` varchar(10) NOT NULL,
  PRIMARY KEY (`student_id`),
  CONSTRAINT `auditinfo_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auditinfo"
#


#
# Source for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(8) NOT NULL,
  `password` varchar(12) DEFAULT NULL,
  `name` varchar(5) DEFAULT NULL,
  `facultyID` varchar(8) DEFAULT NULL,
  `seniority` varchar(10) DEFAULT NULL,
  `contactInfo` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `facultyID` (`facultyID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "teacher"
#

/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('2001001','chendaoxu','陈道蓄','001','教授','email:zy@nju.edu.com'),('2001002','liuhuanyu','刘环宇','001','副教授','无'),('2001003','liuqin','刘钦','001','讲师','qinliu@software.nju.edu.cn'),('2001004','rentongwei','任烔伟','001','讲师','tongwr@software.nju.edu.cn'),('2001005','wanghaoran','王浩然','001','副教授','haoranwang@software.nju.edu.cn'),('2001006','shaodong','邵栋','001','副教授','无'),('2001007','zhengtao','郑滔','001','副教授','无'),('2001008','liujia','刘嘉','001','讲师','无'),('2001009','dingeryu','丁二玉','001','副教授','无'),('2002001','xiaotian','小天','002','副教授','secret'),('2002002','xiaobu','小布','002','讲师','xb@art.nju.edu.cn'),('2003001','xiaomei','小美','003','讲师','无'),('2003002','xiaohao','小浩','003','讲师','ph@nju.edu.cn'),('2004001','xiaolan','小兰','004','讲师','xl@nju.edu.cn'),('2004002','chenlin','陈琳','004','讲师','chenlin@nju.edu.cn'),('2005001','yangdanwei','杨丹伟','005','副教授','无'),('2005002','xiongqiulian','熊秋良','005','讲师','无'),('2006001','shangrong','尚荣','006','教授','无'),('2006002','zhangliang','张亮','006','讲师','无'),('2007001','dinghegen','丁和根','007','副教授','无'),('2007002','wanglei','王蕾','007','讲师','无'),('2008001','zhangrenshan','张仁善','008','讲师','无'),('2008002','liuyong','刘勇','008','副教授','无'),('2009001','gaobo','高波','009','教授','无'),('2009002','luxiao','陆晓','009','讲师','无'),('2010001','chenmin','陈民','010','讲师','无'),('2010002','jinchengyu','金成宇','010','副教授','无'),('2011001','yuequan','岳泉','011','讲师','无'),('2011002','lumingxin','卢明欣','011','副教授','无'),('2013001','shaorong','邵荣','013','副教授','无'),('2013002','wangyiqian','王奕倩','013','副教授','无'),('2019001','xiaopeng','肖鹏','019','讲师','无'),('2019002','chengyan','程燕','019','讲师','无'),('2019003','lichenliang','李晨亮','019','讲师','无'),('2020001','guojichun','郭继春','020','讲师','无');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

#
# Source for table "tempselection"
#

DROP TABLE IF EXISTS `tempselection`;
CREATE TABLE `tempselection` (
  `student_id` varchar(11) CHARACTER SET gbk NOT NULL DEFAULT '',
  `course_id` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `stugrade` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  KEY `index1` (`student_id`),
  KEY `index2` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "tempselection"
#

/*!40000 ALTER TABLE `tempselection` DISABLE KEYS */;
INSERT INTO `tempselection` VALUES ('10001001','c200301',0,'大四上'),('10001001','c100103',0,'大四上'),('10001001','c200402',0,'大四上');
/*!40000 ALTER TABLE `tempselection` ENABLE KEYS */;

#
# Source for view "coursecount"
#

DROP VIEW IF EXISTS `coursecount`;
CREATE VIEW `coursecount` AS 
  select `coursesystem`.`course`.`module` AS `module`,count(`coursesystem`.`course`.`module`) AS `COUNT(``module``)` from `coursesystem`.`course` group by `coursesystem`.`course`.`module`;

#
# Source for view "managerregistry"
#

DROP VIEW IF EXISTS `managerregistry`;
CREATE VIEW `managerregistry` AS 
  select `coursesystem`.`manager`.`id` AS `id`,`coursesystem`.`manager`.`password` AS `password` from `coursesystem`.`manager`;

#
# Source for view "scoreinfo"
#

DROP VIEW IF EXISTS `scoreinfo`;
CREATE VIEW `scoreinfo` AS 
  select `coursesystem`.`student`.`id` AS `student_id`,`coursesystem`.`course`.`name` AS `course_name`,`coursesystem`.`course`.`module` AS `course_module`,`coursesystem`.`selectcourserecord`.`score` AS `score` from ((`coursesystem`.`selectcourserecord` join `coursesystem`.`student`) join `coursesystem`.`course`) where ((`coursesystem`.`selectcourserecord`.`student_id` = `coursesystem`.`student`.`id`) and (`coursesystem`.`selectcourserecord`.`course_id` = `coursesystem`.`course`.`id`) and (`coursesystem`.`selectcourserecord`.`score` is not null));

#
# Source for view "senioritycount"
#

DROP VIEW IF EXISTS `senioritycount`;
CREATE VIEW `senioritycount` AS 
  select `coursesystem`.`teacher`.`seniority` AS `seniority`,count(`coursesystem`.`teacher`.`seniority`) AS `COUNT(``seniority``)` from `coursesystem`.`teacher` group by `coursesystem`.`teacher`.`seniority`;

#
# Source for view "studentregistry"
#

DROP VIEW IF EXISTS `studentregistry`;
CREATE VIEW `studentregistry` AS 
  select `coursesystem`.`student`.`id` AS `id`,`coursesystem`.`student`.`password` AS `password` from `coursesystem`.`student`;

#
# Source for view "teachercount"
#

DROP VIEW IF EXISTS `teachercount`;
CREATE VIEW `teachercount` AS 
  select `coursesystem`.`teacher`.`facultyID` AS `facultyID`,count(`coursesystem`.`teacher`.`facultyID`) AS `COUNT(``facultyID``)` from `coursesystem`.`teacher` group by `coursesystem`.`teacher`.`facultyID`;

#
# Source for view "teacherregistry"
#

DROP VIEW IF EXISTS `teacherregistry`;
CREATE VIEW `teacherregistry` AS 
  select `coursesystem`.`teacher`.`id` AS `id`,`coursesystem`.`teacher`.`password` AS `password` from `coursesystem`.`teacher`;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
