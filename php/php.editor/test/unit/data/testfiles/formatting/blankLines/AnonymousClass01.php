<?php
$instance1 = new class {
   public function test() {
       echo "{}::test() called\n";
   }

   // something
   final public function moreTesting() {
       echo "{}::moreTesting() called\n";
   }
};
$instance2 = new class extends BaseClass {
    private $field1;
    var $field2;
    public function method1() {
    }
};






$instance3 = new class extends BaseClass {




    
    public $field2 = 22;
};
