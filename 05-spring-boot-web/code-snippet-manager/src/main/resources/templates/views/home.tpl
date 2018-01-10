layout 'layouts/main.tpl',
        pageTitle: 'Code Snippet Manager',
        mainBody: contents {
        		div(class: 'col-xs-12 col-sm-9'){
        
        			p(class: 'pull-right visible-xs') {
        				button(type: 'button', class: 'btn btn-primary btn-xs', 'data-toggle':'offcanvas', 'Toggle nav')
        			}	
        			
        			div(class: 'jumbotron') {
            			h1('Welcome')
            			p('A new way to manage your code. Introducing: Code Snippet Manager!!')
            		}
            		
            		div(class: 'row') {
            		
            			snippets.each { snippet ->
	            			div(class: 'col-xs-6 col-lg-4'){
	            				h3(snippet.lang.name + ": " + snippet.title)
	            				p{
	            					pre(class: 'brush: ' + snippet.lang.syntax) {
	            						
	            						yieldUnescaped tryEscape(snippet.code.source)
	            						
	            					}
	            				}
	            				//p{
	            				//	a(class: 'btn btn-default', href:'#', role:'button', 'View details &raquo;')
	            				//}
	            			}
            			}
            		}
            }
            
            div(class: 'col-xs-6 col-sm-3 sidebar-offcanvas', id:'sidebar') {
            		div(class: 'list-group') {
            			
            			  langs.each { lang ->
            		      		a(href: '#', class: 'list-group-item', lang.name)
            		      }
            		    
            		}
            }
            
            
            
        }