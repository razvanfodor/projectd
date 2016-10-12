/* global app */
    
app.factory('WebService', function ($http, $q) {
    
    /**
     * Base path of the application. Will always end with a '/'.
     */
    var BASE_PATH = detectBasePath();
    var AUTH_TOKEN_SESSION_KEY = 'projectD.authToken';

    function detectBasePath(){
        var path = window.location.pathname;

        var basePathIndex = path.indexOf('projectD');
        if(basePathIndex === -1){
            return '/';
        }
        else{
            return path.substring(0, basePathIndex);
        }
    }


    function get(webServicePath, parameters) {
        var config = {
            webServicePath: webServicePath,
            parameters: parameters
        };

        config.method = 'GET';

        return ajax(config);
    }

    function post(webServicePath, data, parameters) {
        var config = {
            webServicePath: webServicePath,
            data: data,
            parameters: parameters
        };

        config.method = 'POST';

        return ajax(config);
    }

    function put(webServicePath, data, parameters) {
        var config = {
            webServicePath: webServicePath,
            data: data,
            parameters: parameters
        };

        config.method = 'PUT';

        return ajax(config);
    }

    function del(webServicePath, parameters){
        var config = {
            webServicePath: webServicePath,
            parameters: parameters
        };

        config.method = 'DELETE';

        return ajax(config);
    }

    function ajax(config){
        var deferred = $q.defer();

        var $httpConfig = {
            method: config.method,
            url: buildUrl(config.webServicePath, config.parameters)
        };

        if(config.data !== undefined){
            $httpConfig.data = config.data;
        }
        
        var authToken = window.sessionStorage.getItem(AUTH_TOKEN_SESSION_KEY);
        if (authToken !== null){
            $httpConfig.headers = {'auth_token' : authToken}
        }

        $http($httpConfig)
            .success(buildSuccessHandler(deferred))
            .error(buildErrorHandler(deferred));

        return deferred.promise;
    }

    function buildSuccessHandler(deferred) {
        return function (data) {
            deferred.resolve(data);
        };
    }

    function buildErrorHandler(deferred) {
        return function (data) {
            deferred.reject(data);
        };
    }
    
    function buildUrl(webServicePath, clientParameters){
        var serverParameters = clientParameters;

        return buildUrlFromWebservicePath(webServicePath, serverParameters) + buildQueryParameters(removeEmptyProperties(serverParameters));
    }
    
    function buildUrlFromWebservicePath(webServicePath, parameters) {
        return BASE_PATH + 'projectd/service/' + evaluateWebServicePath(webServicePath, parameters);
    }
    
    function evaluateWebServicePath(webServicePath, parameters) {
        var result = webServicePath;
        for (var key in parameters) {
            if (!parameters.hasOwnProperty(key)){
                continue;
            }

            var pattern = new RegExp("{" + key + "}");
            if (result.match(pattern)) {
                var value = parameters[key];

                result = result.replace(pattern, value);
                delete parameters[key];
            }
        }

        return result;
    }
    
    function buildQueryParameters(parameters) {
        var s = '';

        for (var key in parameters) {
            if(!parameters.hasOwnProperty(key)){
                continue;
            }

            var value = parameters[key];

            var prefix = (s === "") ? '?' : '&';
            s += prefix + key + '=' + encodeURIComponent(value);
        }

        return s;
    }

    function removeEmptyProperties(inputObject){
        var object = angular.copy(inputObject);

        for(var key in object){
            if (object.hasOwnProperty(key)){
                var value = object[key];

                if(value === undefined || value === null || value == ''){
                    delete object[key];
                }
            }
        }

        return object;
    }
    
    return {
        get: get,
        post: post,
        put: put,
        delete: del        
    };
});
