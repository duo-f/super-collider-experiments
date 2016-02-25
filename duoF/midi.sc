s.reboot;

MIDIClient.init;
MIDIClient.sources.last
MIDIIn.connect(0, MIDIClient.sources.last);


(
SynthDef(\sinte, {
	arg freq, mul;
	Out.ar(0,
		Impulse.ar(freq: freq, mul: mul)!2
	)
}).send(s);
)


x = Synth(\sinte);

y = MIDIFunc.cc({ |val, num, chan, src|
	[val, num, chan, src].postln;

	switch(num,
		0, { x.set('freq', val) },
	    1, { x.set('mul', val / 127) }
	);
});
