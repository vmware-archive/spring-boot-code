layout 'layouts/main.tpl',
        pageTitle: 'Web Directory',
        mainBody: contents {
        		div(class: 'col-xs-12 col-sm-12'){
        
        			p(class: 'pull-right visible-xs') {
        				button(type: 'button', class: 'btn btn-primary btn-xs', 'data-toggle':'offcanvas', 'Toggle nav')
        			}	
        			
        			div(class: 'jumbotron') {
            			h1('Web Directory')
            			p('A new way to save your contacts information!')
            		}
            		
            		div(class: 'row') {
            			
            		}
            }
            
        }