(set-env!
 :resource-paths #{"src" "resources"}
 :dependencies '[[boot/core "2.5.5" :scope "provided"]
                 [org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.228" :scope "provided"]
                 [org.clojure/tools.namespace "0.3.0-alpha3"]
                 [org.pegdown/pegdown "1.4.2"]
                 [leinjacker "0.4.2"]
                 [hiccup "1.0.5"]
                 [funcool/bootutils "0.2.0" :scope "test"]
                 [adzerk/bootlaces "0.1.13" :scope "test"]])

(require
  '[funcool.boot-codeina :refer [apidoc]]
  ;; '[funcool.bootutils :refer :all]
  '[adzerk.bootlaces :refer :all])

(def +version+
  "0.2.1-SNAPSHOT")

(def +description+
  "A tool for generating API documentation from Clojure")

(bootlaces! +version+)

(task-options!
 pom  {:project     'net.info9/boot-codeina
       :version     +version+
       :description +description+
       :url         "https://github.com/tmarble/boot-codeina"
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}
       :scm         {:url "https://github.com/tmarble/boot-codeina"}}
 apidoc {:version +version+
         :title "Boot-Codeina"
         :sources #{"src"}
         :src-uri "https://github.com/tmarble/boot-codeina/tree/master/"
         :src-uri-prefix "#L"
         :description +description+})

(deftask build
  "Build jar and install to local repo."
  []
  (comp
    (sift :include #{#".*~$"} :invert true) ;; don't include emacs backups
    (pom)
    (jar)
    (install)))
