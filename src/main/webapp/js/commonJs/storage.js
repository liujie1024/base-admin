/*-----------------cookie---------------------*/
/* 设置cookie */
function setCookie(name, value, day) {
	var setting = arguments[0];
	if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object') {
		for ( var i in setting) {
			var oDate = new Date();
			oDate.setDate(oDate.getDate() + day);
			document.cookie = i + '=' + setting[i] + ';expires=' + oDate;
		}
	} else {
		var oDate = new Date();
		oDate.setDate(oDate.getDate() + day);
		document.cookie = name + '=' + value + ';expires=' + oDate;
	}
}

/* 获取cookie */
function getCookie(name) {
	var arr = document.cookie.split('; ');
	for (var i = 0; i < arr.length; i++) {
		var arr2 = arr[i].split('=');
		if (arr2[0] == name) {
			return arr2[1];
		}
	}
	return '';
}

/* 删除cookie */
function removeCookie(name) {
	this.setCookie(name, 1, -1);
}

/*-----------------localStorage---------------------*/
/* 设置localStorage */
function setLocal(key, val) {
	var setting = arguments[0];
	if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object') {
		for ( var i in setting) {
			this.ls.setItem(i, JSON.stringify(setting[i]));
		}
	} else {
		this.ls.setItem(key, JSON.stringify(val));
	}

}

/* 获取localStorage */
function getLocal(key) {
	if (key) {
		return JSON.parse(this.ls.getItem(key))
	}
	return null;

}

/* 移除localStorage */
function removeLocal(key) {
	this.ls.removeItem(key);
}

/* 移除所有localStorage */
function clearLocal() {
	this.ls.clear();
}

/*-----------------sessionStorage---------------------*/
/* 设置sessionStorage */
function setSession(key, val) {
	var setting = arguments[0];
	if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object') {
		for ( var i in setting) {
			this.ss.setItem(i, JSON.stringify(setting[i]));
		}
	} else {
		this.ss.setItem(key, JSON.stringify(val));
	}

}

/* 获取sessionStorage */
function getSession(key) {
	if (key) {
		return JSON.parse(this.ss.getItem(key))
	}
	return null;
}

/* 移除sessionStorage */
function removeSession(key) {
	this.ss.removeItem(key);
}

/* 移除所有sessionStorage */
function clearSession() {
	this.ss.clear();
}
