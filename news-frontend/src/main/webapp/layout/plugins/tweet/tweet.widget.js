function AddTweet(TU,NP){
	new TWTR.Widget({
	  version: 2,
	  type: 'profile',
	  rpp: NP,
	  interval: 30000,
	  width: 'auto',
	  height: 'auto',
	  theme: {
		shell: {
		  background: 'none'
		},
		tweets: {
		  background: 'none'
		}
	  },
	  features: {
		scrollbar: false,
		loop: false,
		live: false,
		behavior: 'all'
	  },
	  ready: function() {
	  	var path = $('.block_twitter_widget .twtr-join-conv').attr('href');
		$('.block_twitter_widget .lnk_follow a').attr('href', path);
	  }
	}).render().setUser(TU).start();
}