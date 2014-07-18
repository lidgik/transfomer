CREATE TABLE `group` (
  `id_group` int(11) NOT NULL AUTO_INCREMENT,
  `name_group` varchar(20) DEFAULT NULL,
  `memo_group` varchar(100) DEFAULT NULL,
  `parent_group` varchar(20) DEFAULT NULL,
  `address_group` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;