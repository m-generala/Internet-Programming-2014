$(document).ready(function () {
    var paragraphInCol1 = $('#col1 p');
    console.log(paragraphInCol1.html());

    var tuLink = $('#footer a:first-child');
    console.log(tuLink.attr('title'));
});