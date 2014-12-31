/// <reference path="../libs/jquery-2.1.3.min.js" />

$(document).ready(function () {
    function getFirstFivePosts() {
        $.get('http://jsonplaceholder.typicode.com/posts', function (data) {
            addToPosts(data);
        });
    }

    function addToPosts(posts) {
        var postsUl = $('#posts'),
            i = 0;

        $.each(posts, function () {
            appendToList(postsUl, this);
            if (++i >= 5) {
                return false;
            }
        });
    }

    function appendToList(list, post) {
        var newElement = $("<li/>");
        newElement.text(post.title);
        list.append(newElement);
    }

    function createError() {
        var errorDiv = $('<div>'),
            dynamicContent = $('#dynamiccontent');

        if ($('#errordiv').html() !== undefined) {
            return;
        }

        errorDiv.attr('id', 'errordiv');
        errorDiv.html('Please enter some text!!!');
        dynamicContent.prepend(errorDiv);
    }

    function addNewPostToList(data) {
        var id = data.id;
        $.get('http://jsonplaceholder.typicode.com/posts/' + id).then(function (data) {
            var list = $('#posts'),
                newElement = $("<li/>");

            newElement.attr('id', 'post' + id);
            newElement.text(data.body);
            addDeleteButton(newElement, id);
            list.append(newElement);
        });
    }

    function addPostFunctionality() {
        var button = $('#addbutton');
        button.click(function () {
            var input = $('#textinput');
            var inputValue = input.val();

            if (inputValue === undefined || inputValue === '') {
                createError();
            } else {
                var userId = Math.floor((Math.random() * 10) + 1),
                    data = {
                        userId: userId,
                        title: 'My cool title',
                        body: inputValue
                    };

                if ($('#errordiv').html() !== undefined) {
                    $('#errordiv').remove();
                }

                $.post('http://jsonplaceholder.typicode.com/posts', data, addNewPostToList, 'json')
                    .error(function (err) {
                        alert(err);
                    });
            }
        });
    }

    getFirstFivePosts();
    addPostFunctionality();
});

function addDeleteButton(linkToAddTo, id) {
    var button = $('<button>');
    button.html('x');
    button.attr('data-id', id);

    button.click(function () {
        var willDelete = confirm("Are you sure you want to delete the post?");
        if (willDelete === true) {
            var id = button.attr('data-id');

            $.ajax({
                url: 'http://jsonplaceholder.typicode.com/posts/' + id,
                type: 'DELETE',
                success: function (result) {
                    $('#post' + id).remove();
                }
            });
        }
    });

    linkToAddTo.append(button);
}