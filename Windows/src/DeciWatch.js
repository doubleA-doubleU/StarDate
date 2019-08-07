function init() {
	// format gadget size
	document.body.style.width = 185;
	document.body.style.height = 76;
	
	// run the main function
	main();
}

function main() {
	// get the current time
	var date = new Date();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	
	// convert time to seconds, then dours/dinutes/deconds
	var sec = h*3600 + m*60 + s;
	var dec = sec/0.864;
	var dh = Math.floor(dec/10000);
	var dm = Math.floor((dec - dh*10000)/100);
	var ds = Math.floor(dec-dh*10000-dm*100);
	
	// format with leading zero if less than 10
	h = (h < 10)? "0" + h : h;
	m = (m < 10)? "0" + m : m;
	s = (s < 10)? "0" + s : s;
	dm = (dm < 10)? "0" + dm : dm;
	ds = (ds < 10)? "0" + ds : ds;
	
	// construct times as strings with colon separators
	var time = h + ":" + m + ":" + s;
	var dtime = "0" + dh + ":" + dm + ":" + ds;
	
	// make variables available to html
	document.getElementById("time").innerHTML = time;
	document.getElementById("dtime").innerHTML = dtime;
	
	// update every 0.1 decimal seconds
	setTimeout(main,86.4);
}