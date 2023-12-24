//获取当前时间（XXXX-XX-XX）
export function getCurDate() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;
    return year + "-" + month + "-" + day;
}

// 向sessionStorage中存储一个JSON对象,,Storage存储，setSessionStorage客户端的存储
// Local Storage永久存储，类似于在网上看个视频，下次打开可以跳转到上次观看的进度
// SessionStorage 临时存储，关闭浏览器或者退出登录，数据就没有了，类似于有网的时候打开一个页面，断网了还能看，
// 但是关了之后就不能看了，除非下次有网再次加载
export function setSessionStorage(keyStr, value) {
    sessionStorage.setItem(keyStr, JSON.stringify(value));
}

// 从sessionStorage中获取一个JSON对象（取不到时返回null）
export function getSessionStorage(keyStr) {
    var str = sessionStorage.getItem(keyStr);
    if (str == '' || str == null || str == 'null' || str == undefined) {
        return null;
    } else {
        return JSON.parse(str);
    }
}

// 从sessionStorage中移除一个JSON对象
export function removeSessionStorage(keyStr) {
    sessionStorage.removeItem(keyStr);
}

// 向localStorage中存储一个JSON对象
export function setLocalStorage(keyStr, value) {
    localStorage.setItem(keyStr, JSON.stringify(value));
}

// 从localStorage中获取一个JSON对象（取不到时返回null）
export function getLocalStorage(keyStr) {
    var str = localStorage.getItem(keyStr);
    if (str == '' || str == null || str == 'null' || str == undefined) {
        return null;
    } else {
        return JSON.parse(str);
    }
}

// 从localStorage中移除一个JSON对象
export function removeLocalStorage(keyStr) {
    localStorage.removeItem(keyStr);
}
