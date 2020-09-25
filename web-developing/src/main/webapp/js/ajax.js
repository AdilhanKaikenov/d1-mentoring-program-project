function _reloadNamesList(json) {
    var responseDiv = $('#js-ajax-get-servlet-response');
    responseDiv.empty();
    $.each(json, function(id, name) {
        responseDiv.append(
        "<li data-id=" + id + " style='display: inline;' >" + name +
        "</li> - <a href='#' class='js-delete-username'>Ajax DELETE request</a><br>");
    });
}

function updateAccessCountSection() {
    var visitCount = $.cookie('visit_count');
    $('#js-visit-count-section').empty().append(visitCount);
}

$(document).ready(function() {
    updateAccessCountSection();

    $('#js-show-list').click(function(e) {
        e.preventDefault();

        $.ajax({
            type: "GET",
            url : '/myServlet',
            success : function(responseJson) {
               _reloadNamesList(responseJson);
            }
        });
    });

    $('#addUsername').submit(function(e) {
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url : '/myServlet',
            data: $('#addUsername').serialize(),
            success : function(responseJson) {
                _reloadNamesList(responseJson);
            }
        });
    });

    $('#updateUsername').submit(function(e) {
        e.preventDefault();

        var id = $('#js-update-user-id-filed').val();
        var newname = $('#js-update-new-name-filed').val();

        var data = { 'id' : id, 'newname' : newname };

        $.ajax({
            type: 'PUT',
            url : '/myServlet',
            dataType: 'json',
            data: JSON.stringify(data)
        });
    });

    $('#js-ajax-get-servlet-response').on('click', '.js-delete-username', function(e) {
        e.preventDefault();

        var targetElement = $(this).prev();
        var id = targetElement.data('id');

        $.ajax({
            type: 'DELETE',
            url : '/myServlet' + '?' + $.param({"id": id}),
            dataType: 'json',
            success : function(responseJson) {
                _reloadNamesList(responseJson);
            }
        });
    });

    $('#js-ajax-get-servlet-response').on('click', 'li', function(e) {
        var targetElement = $(this);
        var id = targetElement.data('id');
        var name = targetElement.text();

        $('#js-update-user-id-filed').val(id);
        $('#js-update-old-name-filed').val(name);
    });
});