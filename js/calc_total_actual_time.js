$(function() {
	var actual_total = 0;
	var remaining_total = 0;
	$('.taskBoardTaskRemainingWorkValue').each(function (idx, ele) {
		txt = $(ele).text();
		// console.log('#' + idx + ': ' + txt);
		var items = txt.split(' ');
		// txt.indexOf('actual') != -1
		if (items[1] == 'actual') {
			// console.log('actual: ' + txt);
			actual_total += new Number(items[0]);
		} else if (items[1] == 'remaining') {
			// console.log('remaining: ' + txt);
			remaining_total += new Number(items[0]);
		}
	});
	console.log('actual_total=' + actual_total);
	console.log('remaining_total=' + remaining_total);
	alert('actual_total=' + actual_total + ', remaining_total=' + remaining_total);
});
/* add following to Agile Task Board.html
<script type="text/javascript" language="javascript" src="D:/sourcecode/wutalk.github.io/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" language="javascript" src="D:/sourcecode/wutalk.github.io/js/calc_total_actual_time.js"></script>
*/