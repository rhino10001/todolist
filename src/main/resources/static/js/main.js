$(function(){

    const appendDoing = function(data){
        var doingCode = '<a href="#" class="doing-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#doing-list')
            .append('<div>' + doingCode + '</div>');
    };

    //Show adding book form
    $('#show-add-doing-form').click(function(){
        $('#doing-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#doing-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.doing-link', function(){
        var link = $(this);
        var doingId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/doings/' + doingId,
            success: function(response)
            {
                var code = '<span>' + response.content + '<br>' + 'Deadline: ' + response.term + '<br>' + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Doing is not found!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-doing').click(function()
    {
        var data = $('#doing-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/doings/',
            data: data,
            success: function(response)
            {
                $('#doing-form').css('display', 'none');
                var doing = {};
                doing.id = response;
                var dataArray = $('#doing-form form').serializeArray();
                for(i in dataArray) {
                    doing[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendDoing(doing);
            }
        });
        return false;
    });

});