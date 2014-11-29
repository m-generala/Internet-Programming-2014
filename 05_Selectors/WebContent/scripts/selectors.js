$(document).ready(function () {
    "use strict"
    //i take the first element so it
    //looks a bit better logged
    var body = $('.home')[0],
        divHeader = $('#header')[0],
        mainHeader = $('#header h1'),
        menuDiv = $('#header .menu-top-level-menu-container')[0],
        inscreenDiv = $('#home .inscreen')[0],
        linkInCol1 = $('#col1 h2 a')[0],
        labelFromForm = $('#mc-embedded-subscribe-form .mc-field-group label')[0],
        strongInFooter = $('#footer strong')[0],
        somethingRandom = $('[title*="Технически Университет - София"]')[0],
        allInputs = $(':input')[0];

    console.log(body + ' ' + divHeader + ' ' + mainHeader
        + ' ' + menuDiv + ' ' + inscreenDiv + ' ' + linkInCol1
        + ' ' + labelFromForm + ' ' + strongInFooter + ' ' + somethingRandom
        + ' ' + allInputs);
});
//And so on and so on