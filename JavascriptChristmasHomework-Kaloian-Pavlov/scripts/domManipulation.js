/// <reference path="../libs/jquery-2.1.3.min.js" />
$(document).ready(function () {
    function addButton() {
        var unorderedList = $('#menu-top-level-menu'),
            listElement = $('<li>'),
            linkElement = $('<a>');
        
        linkElement.html('new button');
        listElement.append(linkElement);
        unorderedList.append(listElement);
    }

    //prepends
    function addDivToFooter() {
        var footer = $('#footer'),
            div = $('<div>'),
            input = $('<input>'),
            button = $('<button>'),
            ul = $('<ul>');

        input.attr('id', 'textinput');
        ul.attr('id', 'posts');
        div.attr('id', 'dynamiccontent');

        button.attr('id', 'addbutton');
        button.html('Send');

        div.append(input);
        div.append(button);
        div.append(ul);
        footer.prepend(div);
    }

    addButton();
    addDivToFooter();
});