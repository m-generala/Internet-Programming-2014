// please choose your host here !!!
var host = 'http://jsonplaceholder.typicode.com/posts';

$(document).ready(function () {
    var paragraphInCol1 = $('#col1 p'),
        tuLink = $('#footer a:first-child');
    
    console.log(paragraphInCol1.html());
    console.log(tuLink.attr('title'));
});
