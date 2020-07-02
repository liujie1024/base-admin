drop table base_user;
create table base_user  (
    id VARCHAR2(50) NOT NULL,
    username VARCHAR2(100),
    password VARCHAR2(100),
    state INT DEFAULT '0',
    createTime DATE,
    updateTime DATE,
    createUser VARCHAR2(100),
    constraint PK_base_user primary key (id)
);
 
comment on table base_user is'用户信息表';
comment on column base_user.id is '主键id';
comment on column base_user.username is '用户名';
comment on column base_user.password is '密码';
comment on column base_user.state is  '状态';
comment on column base_user.createTime is '创建时间';
comment on column base_user.updateTime is '更新时间';
comment on column base_user.createUser is '创建人';


drop table base_role;
create table base_role  (
    id VARCHAR2(50) NOT NULL,
    rolename VARCHAR2(100),
    createTime DATE,
    updateTime DATE,
    createUser VARCHAR2(100),
    constraint PK_base_role primary key (id)
);
 
comment on table base_role is'角色表';
comment on column base_role.id is '主键id';
comment on column base_role.rolename is '角色名称';
comment on column base_role.createTime is '创建时间';
comment on column base_role.updateTime is '更新时间';
comment on column base_role.createUser is '创建人';
	
	
	

drop table base_menu;	
create table base_menu  (
    id VARCHAR2(50) NOT NULL,
    menunama VARCHAR2(100),
    menuurl VARCHAR2(100),
    parentId VARCHAR2(100),
    createTime DATE,
    updateTime DATE,
    createUser VARCHAR2(100),
    grade VARCHAR2(10),
    auth VARCHAR2(10),
    extend1 VARCHAR2(100),
    extend2 VARCHAR2(500),
    constraint PK_base_menu primary key (id)
);
 
comment on table base_menu is'菜单表';
comment on column base_menu.id is '主键id';
comment on column base_menu.menunama is '菜单名称';
comment on column base_menu.menuurl is 'url地址';
comment on column base_menu.parentId is  '上一级菜单';
comment on column base_menu.createTime is '创建时间';
comment on column base_menu.updateTime is '更新时间';
comment on column base_menu.createUser is '创建人';
comment on column base_menu.grade is '级别，1：一级菜单，2：二级菜单';
comment on column base_menu.auth is '权限区分，page：页面，auth：功能按钮';
comment on column base_menu.extend1 is '扩展字段1';
comment on column base_menu.extend2 is '扩展字段2';
	
	
drop table base_user_role;	
create table base_user_role  (
    id VARCHAR2(50) NOT NULL,
    userId VARCHAR2(100),
	roleId VARCHAR2(100),
    constraint PK_base_user_role primary key (id)
);
 
comment on table base_user_role is'用户角色表';
comment on column base_user_role.id is '主键id';
comment on column base_user_role.userId is '用户id';
comment on column base_user_role.roleId is '角色id';



drop table base_role_menu;	
create table base_role_menu  (
    id VARCHAR2(50) NOT NULL,
    roleId VARCHAR2(100),
	menuId VARCHAR2(100),
    constraint PK_base_role_menu primary key (id)
);
 
comment on table base_role_menu is'角色菜单表';
comment on column base_role_menu.id is '主键id';
comment on column base_role_menu.roleId is '角色id';
comment on column base_role_menu.menuId is '菜单id';



	
INSERT INTO base_user (id, username, password, state, createTime, updateTime, createUser) VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0, sysdate, sysdate, null);

	
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