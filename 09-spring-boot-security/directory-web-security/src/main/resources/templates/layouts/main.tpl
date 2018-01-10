yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title(pageTitle)
        meta(name: 'viewport', content:'width=device-width, initial-scale=1')
        
        link(rel: 'stylesheet', href: 'webjars/bootstrap/3.3.6/css/bootstrap.min.css')
        link(rel: 'stylesheet', href: 'css/offcanvas.css')
        link(rel: 'stylesheet', href: 'css/theme.css')
        
        script(src: 'webjars/jquery/2.2.4/jquery.min.js'){ }
        script(src: 'webjars/bootstrap/3.3.6/js/bootstrap.min.js'){ }
        script(src: 'webjars/angularjs/1.5.7/angular.min.js') { }
        
    }
    body {
    		nav (class: 'navbar navbar-fixed-top navbar-inverse') {
    			div(class: 'container'){
    				div(class: 'navbar-header'){
    					button(type: 'button', class: 'navbar-toggle collapsed', 'data-toggle':'collapse', 'data-target':'#navbar', 'aria-expanded':'false', 'aria-controls':'navbar'){
    						
    					}
    					a(class:'navbar-brand','Web Directory') 
    				}
    			}
    		}
    		div(class: 'container'){
    			div(class: 'row row-offcanvas row-offcanvas-right'){
        			mainBody()
        		}
        		
        		hr()
            
            footer {
            		p('&copy; 2017 Web Directory')
            }
        }
       
    }
}