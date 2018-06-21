'use strict';

$('.datepicker').datepicker({
    language: 'zh-CN',
    startDate: new Date().toISOString().split('T')[0]
});

$('.needs-validation').click(function (event) {
    event.target.closest('form').classList.add('was-validated');
});
