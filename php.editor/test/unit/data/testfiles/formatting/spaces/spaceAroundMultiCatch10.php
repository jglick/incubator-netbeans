<?php
try {
    // something
}catch(          \Test\Sub\ExceptionType1|    \Test\Sub\ExceptionType2        $e) {
echo $e->getTraceAsString();
        } catch (   \Test\Sub\ExceptionType3 |    \Test\Sub\ExceptionType4   
           |\Test\Sub\ExceptionType5 $e) {
echo $e->getTraceAsString();
} finally {
    // test
}
