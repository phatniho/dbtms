(function() {
	function myFramework() {
	}
	myFramework.prototype.getMyJson = function(url, params, fun) {
		let xhl = new XMLHttpRequest();
		xhl.onreadystatechange = function() {
			if (xhl.readyState == 4 && xhl.status == 200) {
				let jText = JSON.parse(xhl.responseText);
				fun(jText);
			}
		}
		let jUrl = url + "?" + params;
		xhl.open("GET", jUrl, true);
		xhl.send();
	}

	var obj = new myFramework();
	window.$ = obj;
})()