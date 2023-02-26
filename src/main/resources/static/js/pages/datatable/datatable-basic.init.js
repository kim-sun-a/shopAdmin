/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: datatable_basic_init
/*************************************************************************************/

// korean
var lang_kor = {
    "decimal": "",
    "emptyTable": "데이터가 없습니다",
    "info": "_START_ - _END_ (총 _TOTAL_ 건)",
    "infoEmpty": "0건",
    "infoFiltered": "(전체 _MAX_ 건 중 검색결과)",
    "infoPostFix" : "",
    "infoThousands": ",",
    "lengthMenu": "_MENU_ 개씩 보기",
    "loadingRecords": "로딩중...",
    "processing": "잠시만 기다려주세요...",
    "search": "검색 :",
    "zeroRecords": "검색 결과가 없습니다",
    "paginate": {
        "first": "첫 페이지",
        "last": "마지막 페이지",
        "next": "다음",
        "previous": "이전"
    },
    "aria": {
        "sortAscending": ": 오름차순 정렬",
        "sortDescending": ": 내림차순 정렬"
    },
} 

/****************************************
 *       Basic Table                   *
 ****************************************/
$(document).ready(function() {
    $('#noticeTable').DataTable({
        aaSorting: [],
        autoWidth: true,
        ordering: false,
        language: lang_kor,
        columnDefs : [
            {targets: 0, className: "text-center"},
            {targets: 2, className: "text-center"},
            {targets: 3, className: "text-center"},
            {targets: 4, className: "text-center"},
        ]
    });

    $('#requestWorkTable').DataTable({
        aaSorting: [],
        autoWidth: true,
        ordering: false,
        language: lang_kor,
        columnDefs : [
            {targets: 0, className: "text-center"},
            {targets: 1, className: "text-center"},
            {targets: 2, className: "text-center"},
            {targets: 3, className: "text-center"},
            {targets: 5, className: "text-center"},
            {targets: 6, className: "text-center"},
        ]
    });
});
