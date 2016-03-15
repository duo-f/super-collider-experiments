s.boot;

MIDIClient.init;
MIDIClient.sources;
MIDIIn.connect(0, MIDIClient.sources[3]);

(
SynthDef(\sinte, {
	arg freq = 64, mul = 0, detune = 0, reverb = 0;

	Out.ar(0, FreeVerb.ar( (BPF.ar(PinkNoise.ar(0.5, (LFSaw.ar([32, 32.01 + detune]) + Pulse.ar([32, 32 + detune]))), freq) * mul ), reverb, room: 105))
}).send(s);

x = Synth(\sinte);

(
y = MIDIFunc.cc({ |val, num, chan, src|
	[val, num, chan, src].postln;

	switch(num,
		1, { x.set('mul', val / 127) },
		2, { x.set('freq', val * 16 + 1) },
		3, { x.set('detune', val / 127) },
		4, { x.set('filter_freq', val * 20) },
		5, { x.set('reverb', val / 127) }
	);
})
)
)