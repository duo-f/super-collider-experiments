s.boot;

(
MIDIClient.init;
MIDIIn.connectAll;

on = MIDIFunc.noteOn({ |veloc, num, chan, src|
	[veloc, num, chan, src].postln;
});
)