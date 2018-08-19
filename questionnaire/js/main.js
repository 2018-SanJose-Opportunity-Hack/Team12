var i = 0;

function duplicate() {
    var original = document.getElementById('dup' + i);
    var clone = original.cloneNode(true); // "deep" clone
    clone.id = "dup" + ++i; // there can only be one element with an ID
    clone.onclick = duplicate; // event handlers are not cloned
    original.parentNode.appendChild(clone);
}