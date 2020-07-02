drop table if exists base_user;
CREATE TABLE
    base_user
    (
        id VARCHAR(50) NOT NULL COMMENT '主键id',
        username VARCHAR(100) COMMENT '用户名',
        password VARCHAR(100) COMMENT '密码',
        state INT DEFAULT '0' COMMENT '状态',
        createTime DATETIME COMMENT '创建时间',
        updateTime DATETIME COMMENT '更新时间',
        createUser VARCHAR(100) COMMENT '创建人',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

drop table if exists base_role;
CREATE TABLE
    base_role
    (
        id VARCHAR(50) NOT NULL COMMENT '主键id',
        rolename VARCHAR(100) COMMENT '角色名称',
        createTime DATETIME COMMENT '创建时间',
        updateTime DATETIME COMMENT '更新时间',
        createUser VARCHAR(100) COMMENT '创建人',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists base_menu;
CREATE TABLE
    base_menu
    (
        id VARCHAR(50) NOT NULL COMMENT '主键id',
        menunama VARCHAR(100) COMMENT '菜单名称',
        menuurl VARCHAR(100) COMMENT 'url地址',
        parentId VARCHAR(100) COMMENT '上一级菜单',
        createTime DATETIME COMMENT '创建时间',
        updateTime DATETIME COMMENT '更新时间',
        createUser VARCHAR(100) COMMENT '创建人',
        grade VARCHAR(10) COMMENT '级别，1：一级菜单，2：二级菜单',
    	auth VARCHAR(10) COMMENT '权限区分，page：页面，auth：功能按钮',
    	extend1 VARCHAR(100) COMMENT '扩展字段1',
    	extend2 VARCHAR(500) COMMENT '扩展字段2',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists base_user_role;
CREATE TABLE
    base_user_role
    (
        id VARCHAR(50) NOT NULL COMMENT '主键id',
        userId VARCHAR(100) COMMENT '用户id',
        roleId VARCHAR(100) COMMENT '角色id',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists base_role_menu;
CREATE TABLE
    base_role_menu
    (
        id VARCHAR(50) NOT NULL COMMENT '主键id',
        roleId VARCHAR(100) COMMENT '角色id',
        menuId VARCHAR(100) COMMENT '菜单id',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

	
INSERT INTO base_user (id, username, password, state, createTime, updateTime, createUser) VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0, '2017-01-23 13:52:24', '2017-01-24 13:54:10', null);

	
INSERT INTO base_role (id, rolename, createTime, updateTime, createUser) VALUES ('1', '超级管理员', null, null, null);
	
	
INSERT INTO BASE_MENU (ID, MENUNAMA, MENUURL, PARENTID, CREATETIME, UPDATETIME, CREATEUSER, GRADE, AUTH, EXTEND1, EXTEND2) VALUES ('1', '系统管理', null, null, null, null, null, '1', 'page', null, null);
INSERT INTO BASE_MENU (ID, MENUNAMA, MENUURL, PARENTID, CREATETIME, UPDATETIME, CREATEUSER, GRADE, AUTH, EXTEND1, EXTEND2) VALUES ('2', '用户管理', '/user/gotoList.do', '1', null, null, null, '2', 'page', null, null);
INSERT INTO BASE_MENU (ID, MENUNAMA, MENUURL, PARENTID, CREATETIME, UPDATETIME, CREATEUSER, GRADE, AUTH, EXTEND1, EXTEND2) VALUES ('3', '角色管理', '/role/gotoList.do', '1', null, null, null, '2', 'page', null, null);
INSERT INTO BASE_MENU (ID, MENUNAMA, MENUURL, PARENTID, CREATETIME, UPDATETIME, CREATEUSER, GRADE, AUTH, EXTEND1, EXTEND2) VALUES ('4', '菜单管理', '/menu/gotoList.do', '1', null, null, null, '2', 'page', null, null);
INSERT INTO BASE_MENU (ID, MENUNAMA, MENUURL, PARENTID, CREATETIME, UPDATETIME, CREATEUSER, GRADE, AUTH, EXTEND1, EXTEND2) VALUES ('5', '其他', null, null, null, null, null, '1', 'page', null, null);


INSERT INTO base_user_role (id, userId, roleId) VALUES ('1', '1', '1');	
	
	
	
INSERT INTO base_role_menu (id, roleId, menuId) VALUES ('1', '1', '1');
INSERT INTO base_role_menu (id, roleId, menuId) VALUES ('2', '1', '2');
INSERT INTO base_role_menu (id, roleId, menuId) VALUES ('3', '1', '3');
INSERT INTO base_role_menu (id, roleId, menuId) VALUES ('4', '1', '4');

