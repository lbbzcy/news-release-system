/*!
 * jQuery Tools v1.2.6 - The missing UI library for the Web
 * 
 * tabs/tabs.js
 * tabs/tabs.slideshow.js
 * tooltip/tooltip.js
 * tooltip/tooltip.dynamic.js
 * tooltip/tooltip.slide.js
 * 
 * NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.
 * 
 * http://flowplayer.org/tools/
 * 
 */
 
 

(function ($) {

	// static constructs
	$.tools = $.tools || {
		version: '1.2.6'
	};

	$.tools.tabs = {

		conf: {
			tabs: 'a',
			current: 'current',
			onBeforeClick: null,
			onClick: null,
			effect: 'default',
			initialIndex: 0,
			event: 'click',
			rotate: false,

			// slide effect
			slideUpSpeed: 400,
			slideDownSpeed: 400,

			// 1.2
			history: false,
			currentClose: false
		},

		addEffect: function (name, fn) {
			effects[name] = fn;
		}

	};

	var effects = {

		// simple "toggle" effect
		'default': function (i, done) {
			this.getPanes().hide().eq(i).show();
			//done.call();
		},

		/*
configuration:
- fadeOutSpeed (positive value does "crossfading")
- fadeInSpeed
*/
		fade: function (i, done) {

			var conf = this.getConf(),
				speed = conf.fadeOutSpeed,
				panes = this.getPanes();

			if (speed) {
				panes.fadeOut(speed);
			} else {
				panes.hide();
			}

			panes.eq(i).fadeIn(conf.fadeInSpeed, done);
		},

		// for basic accordions
		slide: function (i, clickOnCurrent, done) {
			var conf = this.getConf();
			
			if(clickOnCurrent && conf.currentClose) {
					this.getPanes().eq(i).slideToggle(conf.slideDownSpeed, done);
			}
			else {
				this.getPanes().slideUp(conf.slideUpSpeed);
				this.getPanes().eq(i).slideDown(conf.slideDownSpeed, done);
			}
		},

		/**
		 * AJAX effect
		 */
		ajax: function (i, done) {
			this.getPanes().eq(0).load(this.getTabs().eq(i).attr("href"), done);
		}
	};

	/**
	 * Horizontal accordion
	 *
	 * @deprecated will be replaced with a more robust implementation
	 */

	var
	/**
	 * @type {Boolean}
	 *
	 * Mutex to control horizontal animation
	 * Disables clicking of tabs while animating
	 * They mess up otherwise as currentPane gets set *after* animation is done
	 */
	animating,
	/**
	 * @type {Number}
	 *
	 * Initial width of tab panes
	 */
	w;

	$.tools.tabs.addEffect("horizontal", function (i, done) {
		if (animating) return; // don't allow other animations
		var nextPane = this.getPanes().eq(i),
			currentPane = this.getCurrentPane();

		// store original width of a pane into memory
		w || (w = this.getPanes().eq(0).width());
		animating = true;

		nextPane.show(); // hidden by default
		// animate current pane's width to zero
		// animate next pane's width at the same time for smooth animation
		currentPane.animate({
			width: 0
		}, {
			step: function (now) {
				nextPane.css("width", w - now);
			},
			complete: function () {
				$(this).hide();
				done.call();
				animating = false;
			}
		});
		// Dirty hack... onLoad, currentPant will be empty and nextPane will be the first pane
		// If this is the case, manually run callback since the animation never occured, and reset animating
		if (!currentPane.length) {
			done.call();
			animating = false;
		}
	});


	function Tabs(root, paneSelector, conf) {

		var self = this,
			trigger = root.add(this),
			tabs = root.find(conf.tabs),
			panes = paneSelector.jquery ? paneSelector : root.children(paneSelector),
			current;


		// make sure tabs and panes are found
		if (!tabs.length) {
			tabs = root.children();
		}
		if (!panes.length) {
			panes = root.parent().find(paneSelector);
		}
		if (!panes.length) {
			panes = $(paneSelector);
		}


		// public methods
		$.extend(this, {
			click: function (i, e) {

				var tab = tabs.eq(i);
				var clickOnCurrent = false;

				if (typeof i == 'string' && i.replace("#", "")) {
					tab = tabs.filter("[href*=" + i.replace("#", "") + "]");
					i = Math.max(tabs.index(tab), 0);
				}

				if (conf.rotate) {
					var last = tabs.length - 1;
					if (i < 0) {
						return self.click(last, e);
					}
					if (i > last) {
						return self.click(0, e);
					}
				}

				if (!tab.length) {
					if (current >= 0) {
						return self;
					}
					i = conf.initialIndex;
					tab = tabs.eq(i);
				}

				// current tab is being clicked
				if (i === current) clickOnCurrent = true;
				if (clickOnCurrent && !conf.currentClose) {
					return self;
				}

				// possibility to cancel click action
				e = e || $.Event();
				e.type = "onBeforeClick";
				trigger.trigger(e, [i]);
				if (e.isDefaultPrevented()) {
					return;
				}

				// call the effect
				effects[conf.effect].call(self, i, clickOnCurrent, function () {
					current = i;
					// onClick callback
					e.type = "onClick";
					trigger.trigger(e, [i]);
				});

				// default behaviour
				if (clickOnCurrent && conf.currentClose) {
					tab.toggleClass(conf.current);
				}
				else {
					tabs.removeClass(conf.current);
					tab.addClass(conf.current);
				}

				return self;
			},

			getConf: function () {
				return conf;
			},

			getTabs: function () {
				return tabs;
			},

			getPanes: function () {
				return panes;
			},

			getCurrentPane: function () {
				return panes.eq(current);
			},

			getCurrentTab: function () {
				return tabs.eq(current);
			},

			getIndex: function () {
				return current;
			},

			next: function () {
				return self.click(current + 1);
			},

			prev: function () {
				return self.click(current - 1);
			},

			destroy: function () {
				tabs.unbind(conf.event).removeClass(conf.current);
				panes.find("a[href^=#]").unbind("click.T");
				return self;
			}

		});

		// callbacks
		$.each("onBeforeClick,onClick".split(","), function (i, name) {

			// configuration
			if ($.isFunction(conf[name])) {
				$(self).bind(name, conf[name]);
			}

			// API
			self[name] = function (fn) {
				if (fn) {
					$(self).bind(name, fn);
				}
				return self;
			};
		});


		if (conf.history && $.fn.history) {
			$.tools.history.init(tabs);
			conf.event = 'history';
		}

		// setup click actions for each tab
		tabs.each(function (i) {
			$(this).bind(conf.event, function (e) {
				self.click(i, e);
				return e.preventDefault();
			});
		});

		// cross tab anchor link
		panes.find("a[href^=#]").bind("click.T", function (e) {
			self.click($(this).attr("href"), e);
		});

		// open initial tab
		if (location.hash && conf.tabs == "a" && root.find("[href=" + location.hash + "]").length) {
			self.click(location.hash);

		} else {
			if (conf.initialIndex === 0 || conf.initialIndex > 0) {
				self.click(conf.initialIndex);
			}
		}

	}


	// jQuery plugin implementation
	$.fn.tabs = function (paneSelector, conf) {

		// return existing instance
		var el = this.data("tabs");
		if (el) {
			el.destroy();
			this.removeData("tabs");
		}

		if ($.isFunction(conf)) {
			conf = {
				onBeforeClick: conf
			};
		}

		// setup conf
		conf = $.extend({}, $.tools.tabs.conf, conf);


		this.each(function () {
			el = new Tabs($(this), paneSelector, conf);
			$(this).data("tabs", el);
		});

		return conf.api ? el : this;
	};

})(jQuery);


(function(a){var b;b=a.tools.tabs.slideshow={conf:{next:".forward",prev:".backward",disabledClass:"disabled",autoplay:!1,autopause:!0,interval:3e3,clickable:!0,api:!1}};function c(b,c){var d=this,e=b.add(this),f=b.data("tabs"),g,h=!0;function i(c){var d=a(c);return d.length<2?d:b.parent().find(c)}var j=i(c.next).click(function(){f.next()}),k=i(c.prev).click(function(){f.prev()});function l(){g=setTimeout(function(){f.next()},c.interval)}a.extend(d,{getTabs:function(){return f},getConf:function(){return c},play:function(){if(g)return d;var b=a.Event("onBeforePlay");e.trigger(b);if(b.isDefaultPrevented())return d;h=!1,e.trigger("onPlay"),e.bind("onClick",l),l();return d},pause:function(){if(!g)return d;var b=a.Event("onBeforePause");e.trigger(b);if(b.isDefaultPrevented())return d;g=clearTimeout(g),e.trigger("onPause"),e.unbind("onClick",l);return d},resume:function(){h||d.play()},stop:function(){d.pause(),h=!0}}),a.each("onBeforePlay,onPlay,onBeforePause,onPause".split(","),function(b,e){a.isFunction(c[e])&&a(d).bind(e,c[e]),d[e]=function(b){return a(d).bind(e,b)}}),c.autopause&&f.getTabs().add(j).add(k).add(f.getPanes()).hover(d.pause,d.resume),c.autoplay&&d.play(),c.clickable&&f.getPanes().click(function(){f.next()});if(!f.getConf().rotate){var m=c.disabledClass;f.getIndex()||k.addClass(m),f.onBeforeClick(function(a,b){k.toggleClass(m,!b),j.toggleClass(m,b==f.getTabs().length-1)})}}a.fn.slideshow=function(d){var e=this.data("slideshow");if(e)return e;d=a.extend({},b.conf,d),this.each(function(){e=new c(a(this),d),a(this).data("slideshow",e)});return d.api?e:this}})(jQuery);
(function(a){a.tools=a.tools||{version:"v1.2.6"},a.tools.tooltip={conf:{effect:"toggle",fadeOutSpeed:"fast",predelay:0,delay:30,opacity:1,tip:0,fadeIE:!1,position:["top","center"],offset:[0,0],relative:!1,cancelDefault:!0,events:{def:"mouseenter,mouseleave",input:"focus,blur",widget:"focus mouseenter,blur mouseleave",tooltip:"mouseenter,mouseleave"},layout:"<div/>",tipClass:"tooltip"},addEffect:function(a,c,d){b[a]=[c,d]}};var b={toggle:[function(a){var b=this.getConf(),c=this.getTip(),d=b.opacity;d<1&&c.css({opacity:d}),c.show(),a.call()},function(a){this.getTip().hide(),a.call()}],fade:[function(b){var c=this.getConf();!a.browser.msie||c.fadeIE?this.getTip().fadeTo(c.fadeInSpeed,c.opacity,b):(this.getTip().show(),b())},function(b){var c=this.getConf();!a.browser.msie||c.fadeIE?this.getTip().fadeOut(c.fadeOutSpeed,b):(this.getTip().hide(),b())}]};function c(b,c,d){var e=d.relative?b.position().top:b.offset().top,f=d.relative?b.position().left:b.offset().left,g=d.position[0];e-=c.outerHeight()-d.offset[0],f+=b.outerWidth()+d.offset[1],/iPad/i.test(navigator.userAgent)&&(e-=a(window).scrollTop());var h=c.outerHeight()+b.outerHeight();g=="center"&&(e+=h/2),g=="bottom"&&(e+=h),g=d.position[1];var i=c.outerWidth()+b.outerWidth();g=="center"&&(f-=i/2),g=="left"&&(f-=i);return{top:e,left:f}}function d(d,e){var f=this,g=d.add(f),h,i=0,j=0,k=d.attr("title"),l=d.attr("data-tooltip"),m=b[e.effect],n,o=d.is(":input"),p=o&&d.is(":checkbox, :radio, select, :button, :submit"),q=d.attr("type"),r=e.events[q]||e.events[o?p?"widget":"input":"def"];if(!m)throw"Nonexistent effect \""+e.effect+"\"";r=r.split(/,\s*/);if(r.length!=2)throw"Tooltip: bad events configuration for "+q;d.bind(r[0],function(a){clearTimeout(i),e.predelay?j=setTimeout(function(){f.show(a)},e.predelay):f.show(a)}).bind(r[1],function(a){clearTimeout(j),e.delay?i=setTimeout(function(){f.hide(a)},e.delay):f.hide(a)}),k&&e.cancelDefault&&(d.removeAttr("title"),d.data("title",k)),a.extend(f,{show:function(b){if(!h){l?h=a(l):e.tip?h=a(e.tip).eq(0):k?h=a(e.layout).addClass(e.tipClass).appendTo(document.body).hide().append(k):(h=d.next(),h.length||(h=d.parent().next()));if(!h.length)throw"Cannot find tooltip for "+d}if(f.isShown())return f;h.stop(!0,!0);var o=c(d,h,e);e.tip&&h.html(d.data("title")),b=a.Event(),b.type="onBeforeShow",g.trigger(b,[o]);if(b.isDefaultPrevented())return f;o=c(d,h,e),h.css({position:"absolute",top:o.top,left:o.left}),n=!0,m[0].call(f,function(){b.type="onShow",n="full",g.trigger(b)});var p=e.events.tooltip.split(/,\s*/);h.data("__set")||(h.unbind(p[0]).bind(p[0],function(){clearTimeout(i),clearTimeout(j)}),p[1]&&!d.is("input:not(:checkbox, :radio), textarea")&&h.unbind(p[1]).bind(p[1],function(a){a.relatedTarget!=d[0]&&d.trigger(r[1].split(" ")[0])}),e.tip||h.data("__set",!0));return f},hide:function(c){if(!h||!f.isShown())return f;c=a.Event(),c.type="onBeforeHide",g.trigger(c);if(!c.isDefaultPrevented()){n=!1,b[e.effect][1].call(f,function(){c.type="onHide",g.trigger(c)});return f}},isShown:function(a){return a?n=="full":n},getConf:function(){return e},getTip:function(){return h},getTrigger:function(){return d}}),a.each("onHide,onBeforeShow,onShow,onBeforeHide".split(","),function(b,c){a.isFunction(e[c])&&a(f).bind(c,e[c]),f[c]=function(b){b&&a(f).bind(c,b);return f}})}a.fn.tooltip=function(b){var c=this.data("tooltip");if(c)return c;b=a.extend(!0,{},a.tools.tooltip.conf,b),typeof b.position=="string"&&(b.position=b.position.split(/,?\s/)),this.each(function(){c=new d(a(this),b),a(this).data("tooltip",c)});return b.api?c:this}})(jQuery);
(function(a){var b=a.tools.tooltip;b.dynamic={conf:{classNames:"top right bottom left"}};function c(b){var c=a(window),d=c.width()+c.scrollLeft(),e=c.height()+c.scrollTop();return[b.offset().top<=c.scrollTop(),d<=b.offset().left+b.width(),e<=b.offset().top+b.height(),c.scrollLeft()>=b.offset().left]}function d(a){var b=a.length;while(b--)if(a[b])return!1;return!0}a.fn.dynamic=function(e){typeof e=="number"&&(e={speed:e}),e=a.extend({},b.dynamic.conf,e);var f=a.extend(!0,{},e),g=e.classNames.split(/\s/),h;this.each(function(){var b=a(this).tooltip().onBeforeShow(function(b,e){var i=this.getTip(),j=this.getConf();h||(h=[j.position[0],j.position[1],j.offset[0],j.offset[1],a.extend({},j)]),a.extend(j,h[4]),j.position=[h[0],h[1]],j.offset=[h[2],h[3]],i.css({visibility:"hidden",position:"absolute",top:e.top,left:e.left}).show();var k=a.extend(!0,{},f),l=c(i);if(!d(l)){l[2]&&(a.extend(j,k.top),j.position[0]="top",i.addClass(g[0])),l[3]&&(a.extend(j,k.right),j.position[1]="right",i.addClass(g[1])),l[0]&&(a.extend(j,k.bottom),j.position[0]="bottom",i.addClass(g[2])),l[1]&&(a.extend(j,k.left),j.position[1]="left",i.addClass(g[3]));if(l[0]||l[2])j.offset[0]*=-1;if(l[1]||l[3])j.offset[1]*=-1}i.css({visibility:"visible"}).hide()});b.onBeforeShow(function(){var a=this.getConf(),b=this.getTip();setTimeout(function(){a.position=[h[0],h[1]],a.offset=[h[2],h[3]]},0)}),b.onHide(function(){var a=this.getTip();a.removeClass(e.classNames)}),ret=b});return e.api?ret:this}})(jQuery);
(function(a){var b=a.tools.tooltip;a.extend(b.conf,{direction:"up",bounce:!1,slideOffset:10,slideInSpeed:200,slideOutSpeed:200,slideFade:!a.browser.msie});var c={up:["-","top"],down:["+","top"],left:["-","left"],right:["+","left"]};b.addEffect("slide",function(a){var b=this.getConf(),d=this.getTip(),e=b.slideFade?{opacity:b.opacity}:{},f=c[b.direction]||c.up;e[f[1]]=f[0]+"="+b.slideOffset,b.slideFade&&d.css({opacity:0}),d.show().animate(e,b.slideInSpeed,a)},function(b){var d=this.getConf(),e=d.slideOffset,f=d.slideFade?{opacity:0}:{},g=c[d.direction]||c.up,h=""+g[0];d.bounce&&(h=h=="+"?"-":"+"),f[g[1]]=h+"="+e,this.getTip().animate(f,d.slideOutSpeed,function(){a(this).hide(),b.call()})})})(jQuery);