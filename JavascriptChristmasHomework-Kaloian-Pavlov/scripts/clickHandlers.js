/// <reference path="../libs/jquery-1.3.2.min.js" />
$(document).ready(function () {
    function addOnClickTopMenuLink() {
        var linkElement = $('#menu-top-level-menu a:last-child');
        linkElement.click(function () {
            var col1 = $('#col1'),
                col2 = $('#col2'),
                tmp = col1.html();

            col1.html(col2.html());
            col2.html(tmp);
        });
    }

    addOnClickTopMenuLink();
});