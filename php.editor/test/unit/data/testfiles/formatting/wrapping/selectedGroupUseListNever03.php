<?php

namespace GroupUse1;

function test1() {
}
function test2() {
}
function test3() {
}

namespace GroupUse2;

use function GroupUse1\{
    test1,    test2,
    /*FORMAT_START*/test3/*FORMAT_END*/
};
