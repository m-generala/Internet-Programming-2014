$(document).ready(function () {
    var paragraphInCol1 = $('#col1 p'),
        tuLink = $('#footer a:first-child');

    console.log(paragraphInCol1.html());
    console.log(tuLink.attr('title'));
});