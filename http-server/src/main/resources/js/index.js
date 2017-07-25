document.addEventListener('DOMContentLoaded', function () {
    var requestData = function (url, ul) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) {
                return;
            }
            if (xhr.status != 200) {
                console.error(xhr.status + ': ' + xhr.statusText);
            } else {
                var items = JSON.parse(xhr.responseText);
                items.forEach(function (item) {
                    var li = document.createElement('li');
                    li.innerHTML = JSON.stringify(item);
                    ul.appendChild(li);
                });
                if (items.length == 0) {
                    var li = document.createElement('li');
                    li.innerHTML = 'Отсутствуют';
                    ul.appendChild(li);
                }
            }
        }
    }

    var connectedDevices = document.getElementById('connected-devices');
    if (connectedDevices) {
        requestData('/api/devices', connectedDevices);
    }

    var serialPorts = document.getElementById('serial-ports');
    if (serialPorts) {
        requestData('/api/serialports', serialPorts);
    }

    var checkList = document.getElementById('check-list');
    if (checkList) {
        requestData('/api/checklist', checkList);
    }

});