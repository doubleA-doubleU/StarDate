function init() {
	// format gadget size
	document.body.style.width = 195;
	document.body.style.height = 60;
	
	// run the main function
	main();
}

function main() {
	// get the current time
	var dayZero = new Date("04/12/1961");
	var date = new Date();
	var d = Math.floor((date.getTime() - dayZero.getTime())/86400000);
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var ms = date.getMilliseconds();
	
	// convert time to seconds, then "dours/dinutes/deconds"
	var sec = h*3600 + m*60 + s + ms/1000;
	var dec = Math.floor(sec/0.864); // added Math.floor
	
	// construct StarDate as string
	var sdate = d + "." + dec;
	
	// make variable available to html
	document.getElementById("sdate").innerHTML = sdate; // added
	
	// update 9 times per decond
	setTimeout(main,96);
}