/**
 * Created by ZMC on 2017/1/26.
 */
var ul1_firstClick = true;
$('#blog_sort').on('click',function () {

    if (ul1_firstClick){
        $.ajax({
            url : '/getTag',
            type:'get',
            dataType:'json',
            success:function (data,textStatus,jqXHR) {
                $.each(data, function(i, item) {
                    $('#ul1').append("<li><a href='/tag-blogs/"+item.id+"'>"+item.tagname+"("+item.blognum+")</a></li>");
                });
            }
        });
        ul1_firstClick = false;
    }

});

var ul2_firstClick = true;
$('#blog_msort').on('click',function () {
    if (ul2_firstClick){
        $.ajax({
            url : '/getMonthBlogs',
            type:'get',
            dataType:'json',
            success:function (data,textStatus,jqXHR) {
                $.each(data, function(key, value) {
                    $('#ul2').append("<li><a href='/blogs/"+key+"'>"+key+"("+value+")</a></li>");
                });
            }
        });
        ul2_firstClick = false;
    }

});

