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
         button = $('<button>'),
         ul = $('<ul>'),
         div = $('<div>');
     div.attr('id', 'dynamiccontent');
     input.attr('id', 'textinput');
     button.attr('id', 'addbutton');
     button.html('Send');
     ul.attr('id', 'posts');
     div.append(input);
     div.append(button);
     div.append(ul);
     footer.prepend(div);

     var Buttonclick = $('#menu-top-level-menu a:last-child');
     Buttonclick.click(function () {
         var col1 = $('#col1'),
             col2 = $('#col2'),
             tmp = col1.html();
         col1.html(col2.html());
         col2.html(tmp);
     });
     var host = 'http://jsonplaceholder.typicode.com/posts';
     var test = $("#addbutton");
     test.click(function () {
         var inp = $("#textinput"),
         box = inp.val() ;
         
         if (!box) {
             alert("Enter text");
             return;
         }

         
         $.post(host, {
             userId: '5',
             title: 'Title',
             body: box
         },
         function (data, status) {
             if (status) {
                 var list = $("#posts"),
                 newpost = $("<li>"),
                 newelem = $("<a>"),
                 newbutton = $("<button>");
                 newbutton.attr('id', 'dadada');
                 newbutton.html("X");
                 newelem.html(box);
                 newpost.append(newelem);
                 newpost.append(newbutton);
                 list.append(newpost);
             }
         });
         inp.val("");


     });
     var dabutton = $("#dadada");
     dabutton.click(function () {
         alert("deleting");
     });
        
     
 });