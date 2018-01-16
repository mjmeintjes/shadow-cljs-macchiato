(ns funnels-cljs.app
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [funnels-cljs.core-test]))

(doo-tests 'funnels-cljs.core-test)


