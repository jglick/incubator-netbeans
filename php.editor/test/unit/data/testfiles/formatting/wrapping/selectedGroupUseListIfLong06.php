<?php

namespace GroupUse1;

class C1 {
}
class C2 {
}
class C3 {
}
function test1() {
}
function test2() {
}

namespace GroupUse2;

use GroupUse1\{
    C1,
    /*FORMAT_START*/C2,    C3/*FORMAT_END*/
};
use function GroupUse1\{
    test1,
    test2
};
