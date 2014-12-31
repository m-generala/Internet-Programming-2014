 $(document).ready(function () {
    "use strict"
    var a = $("a.tu");
    console.log(a.attr("title"));

    var col = $("#col1 p");
    console.log(col.html());

    var unorderedList = $('#menu-top-level-menu'),
            listElement = $('<li>'),
            linkElement = $('<a>');
     
    linkElement.html('new button');
    listElement.append(linkElement);
    unorderedList.append(listElement);

     var footer = $('#footer'),
         input = $('<input>'),
         div = $('<div>');
     div.attr('id', 'dynamiccontent');
     input.attr('id', 'textinput');
     div.append(input);
     footer.prepend(div);
});