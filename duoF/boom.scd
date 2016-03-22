s.reboot;

MIDIClient.init;
MIDIIn.connect(0, MIDIClient.sources[3]);


(
SynthDef(\boom, {
	arg freq = 64, mul = 0, detune = 0, reverb = 0;

	Out.ar(0, FreeVerb.ar( Pulse.ar([1,1.01,], EnvGen.kr(Env.perc(releaseTime:0.2))), 0.99, room: 105) !2);
}).send(s);

MIDIFunc.noteOn({ |val, num, chan, src|
	[val, num, chan].postln;

	switch(num,
		51, { Synth(\boom) }
	);
});

)
