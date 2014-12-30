/// <reference path="../libs/jquery-2.1.3.min.js" />
$(document).ready(function () {
    function getFirstFivePosts() {
        $.get('http://localhost:3000/posts', function (data) {
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
        if ($('#errordiv').html() !== undefined) {
            return;
        }

        var errorDiv = $('<div>');
        errorDiv.attr('id', 'errordiv');
        var dynamicContent = $('#dynamiccontent');
        errorDiv.html('Please enter some text!!!');
        dynamicContent.prepend(errorDiv);
    }

    function addNewPostToList(data) {
        var id = data.id;
        $.get('http://localhost:3000/posts/' + id).then(function (data) {
            var list = $('#posts');
            var newElement = $("<li/>");
            newElement.attr('id', 'post' + id);
            newElement.text(data.body);
            addDeleteButton(newElement, id);
            list.append(newElement);
        });
    }

    function addPostFunctionality() {
        var button = $('#addbutton');
        console.log(button.html());
        button.click(function () {
            var input = $('#textinput');
            var inputValue = input.val();
            if (inputValue === undefined || inputValue === '') {
                createError();
            } else {
                if ($('#errordiv').html() !== undefined) {
                    $('#errordiv').remove();
                }

                var userId = Math.floor((Math.random() * 10) + 1);
                var data = {
                    userId: userId,
                    title: 'My cool title',
                    body: inputValue
                };

                $.post('http://localhost:3000/posts', data, addNewPostToList, 'json')
                    .error(function (err) {
                        alert(err);
                    });
            }
        });
    }

    function addDeleteButton(linkToAddTo, id) {
        var button = $('<button>');
        button.html('x');
        button.attr('data-id', id);
        button.click(function () {
            var willDelete = confirm("Are you sure you want to delete the post?");
            if (willDelete === true) {
                var id = button.attr('data-id');

                $.ajax({
                    url: 'http://localhost:3000/posts/' + id,
                    type: 'DELETE',
                    success: function (result) {
                        $('#post' + id).remove();
                    }
                });
            }
        });

        linkToAddTo.append(button);
    }

    getFirstFivePosts();
    addPostFunctionality();
});