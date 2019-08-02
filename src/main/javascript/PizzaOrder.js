function checkSelectAtLeastOne(theForm) {
    if (
        theForm.smallSize.checked == false &&
        theForm.mediumSize.checked == false &&
        theForm.largeSize.checked == false &&
        theForm.normalDough.checked == false &&
        theForm.glutenFreeDough.checked == false &&
        theForm.redSauce.checked == false &&
        theForm.whiteSauce.checked == false &&
        theForm.noSauce.checked == false &&
        theForm.cheese.checked == false &&
        theForm.noCheese.checked == false &&
        theForm.mushroom.checked == false &&
        theForm.onions.checked == false &&
        theForm.greenPepper.checked == false &&
        theForm.bacon.checked == false &&
        theForm.pepperoni.checked == false &&
        theForm.sausage.checked == false
    ) {
        alert('You did not select anything! Make Sure you pick one in each category');
        return false;
    } else {
        return true;
    }

}


function selectOneSizeOnly(id) {
    for (var i = 1; i <= 3; i++) {
        document.getElementById(i).checked = false;
    }
    document.getElementById(id).checked = true;
}

function selectOneDoughOnly(id) {
    for (var i = 4; i <= 5; i++) {
        document.getElementById(i).checked = false;
    }
    document.getElementById(id).checked = true;
}

function selectOneSauceOnly(id) {
    for (var i = 6; i <= 8; i++) {
        document.getElementById(i).checked = false;
    }
    document.getElementById(id).checked = true;
}

function cheeseOrNoCheese(id) {
    for (var i = 9; i <= 10; i++) {
        document.getElementById(i).checked = false;
    }
    document.getElementById(id).checked = true;
}