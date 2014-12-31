/// <reference path="../libs/jquery-2.1.3.min.js" />
/// <reference path="ajax.js" />
$(document).ready(function () {
    function addInput(input) {
        input.insertBefore($('#posts'));
        $('<br>').insertBefore(input);
    }

    function createInput() {
        var input = $('<input>');

        input.on('input', function (e) {
            var text = input.val();

            $.get(host + '?userId=' + text)
             .then(function (data) {
                 var postsUl = $('#posts'),
                     li;
                 postsUl.html('');

                 $.each(data, function () {
                     li = $('<li>');
                     li.attr('id', 'post' + this.id);
                     li.html(this.title);

                     addDeleteButton(li, this.id);
                     postsUl.append(li);
                 });
             }, function (err) {
                 alert('Something went wrong!');
             });
        });
        return input;
    }

    addInput(createInput());
});