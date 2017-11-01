// Place third party dependencies in the lib folder
//
// Configure loading modules from the lib directory,
// except 'app' ones, 
requirejs.config({
    "baseUrl": "static/js/lib",
    "paths": {
      "app": "../app",
      "pace":"pace.min",
      "bootstrap":"bootstrap.min",
       "echarts":"echarts/echarts",
       "jquery-ui": "jqGrid/jquery.jqueryui",
       "jqGrid":"jqGrid/jquery.jqGrid",
       "jqgriden": 'jqGrid/i18n/grid.locale-en'
    },
    "shim": {
        "jquery.beta": ["jquery"],
        "bootstrap": ['jquery'],
        "core.ui":["jquery"],
        "bootstrap-table":["jquery"]
    }
});

requirejs(["pace","bootstrap","core.ui"]);
requirejs(["bootstrap"]);
requirejs(["bootstrap-table"]);
//requirejs(["jqgriden","jqGrid"]);


