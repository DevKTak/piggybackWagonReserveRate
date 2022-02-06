<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="https://rawgit.com/vitmalina/w2ui/master/dist/w2ui.min.css" />
    <style>
        .w2ui-col-header {
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div style="display: flex; width: 100%;">
    <div id="form" style="width: 300px; height: 200px;" name="form" class="w2ui-reset w2ui-form">
        <div class="w2ui-form-box" style="width: 298px; height: 198px;">
            <div class="w2ui-form-header">정비 소요일</div>
            <div class="w2ui-page page-0" style="top: 36px; bottom: 60px;">
                <div class="w2ui-column-container">
                    <div class="w2ui-column col-0">
                        <div class="w2ui-field w2ui-span4">
                            <label for="kyung">경정비</label>
                            <div>
                                <select id="kyung" name="kyung" class="w2ui-input">
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4" selected>4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                </select> 일
                            </div>
                        </div>
                        <div class="w2ui-field w2ui-span4">
                            <label for="jung">중정비</label>
                            <div>
                                <select id="jung" name="jung" class="w2ui-input">
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11" selected>11</option>
                                    <option value="12">12</option>
                                </select> 일
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w2ui-buttons">
                <button name="Reset" onclick="main.reset();" class="w2ui-btn">Reset</button>
                <button name="Save" onclick="main.search();" class="w2ui-btn w2ui-btn-blue">Search</button>
            </div>
        </div>
    </div>

    <div id="grid" style="height: 600px; flex: 1;"></div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/w2ui-1.5.min.js"></script>
<%--<script type="text/javascript" src="https://rawgit.com/vitmalina/w2ui/master/dist/w2ui.min.js"></script>--%>
<script>
    const main = {

        init: () => {
            main.initGrid();
        },

        initGrid: () => {
            $('#grid').w2grid({
                name: 'grid',
                show: {
                    toolbar: true,
                    footer: true
                },
                multiSearch: true,
                searches: [
                    { field: 'recid', label: '일차 ', type: 'int' },
                    { field: 'reserveSum', label: '필요량수', type: 'int' }
                ],
                columns: [
                    { field: 'recid', text: '일차', size: '50px', sortable: true, attr: 'align=center' },
                    { field: 'reserveSum', text: '필요량수', size: '100%', sortable: true, attr: 'align=center' }
                ]
            });
        },

        search: () => {
            w2utils.lock($('#grid'), 'Wait...', { spinner: true, opacity : 1 });

            let grid = w2ui.grid;

            grid.clear();

            $.ajax({
                type: 'get',
                dataType: 'json',
                url: '${pageContext.request.contextPath}/find?kyung=' + $('#kyung option:selected').val() + '&jung=' + $('#jung option:selected').val(),
                success: function (result) {
                    console.log('[main] search - result: ', result);

                    grid.add(result);

                    w2utils.unlock($('#grid'));
                }
            });
        },

        reset: () => {
            $('#kyung').val('4').prop('selected', true);
            $('#jung').val('11').prop('selected', true);
        }
    }

    $(function () {
        const self = main;

        self.init();
    });
</script>
</body>
</html>

