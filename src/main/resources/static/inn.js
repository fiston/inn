'use strict';

$('.datepicker').datepicker({
    language: 'zh-CN',
    startDate: new Date().toISOString().split('T')[0]
});

$('.needs-validation').click(function (event) {
    event.target.closest('form').classList.add('was-validated');
});

$('button[data-toggle="modal"]').click(function (event) {
    var modal = event.target.nextElementSibling;
    $.ajax('vacant/' + modal.id.replace('modal', '')).done(function (data) {
        $(modal).find('select#allocatedRoom').append(data);
    });
});
