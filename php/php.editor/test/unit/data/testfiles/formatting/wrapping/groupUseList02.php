<?php
namespace GroupUse;

use    Classes\{   ClassA,ClassB,         ClassC};
use function Functions\{
    functionA,
        functionB,
        functionC
};
use const    Constants\   {CONSTANT_A, CONSTANT_B, CONSTANT_C,
    CONSTANT_D
};
use Mixed\ { ClassA, const      CONSTANT_A, function    functionA };
