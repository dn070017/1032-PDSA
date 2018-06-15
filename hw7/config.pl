# This is a template config.pl listed with defaults option.
# Working directory will be same as temporal test path.
# Please copy this file to your test path and edit as you want.
#
# 2011-07-17: Multiple compile languages and time/space expr

# Language independent options

#$CheckCmd    = '';
#$TimeLimit   = '3';        # CPU limit, in seconds
#$Timeout     = '10';       # Real time limit, in seconds
#$SpaceLimit  = '16384';        # Virtual Memory limit, in kilo-bytes
#$OutputLimit = '2048';     # File size limit, in 512-byte blocks
#$PreTestCmd  = '';
#$JudgeCmd    = '/usr/bin/diff -q -b -B $test_out $user_out';
#$PostTestCmd = '';
#$ScoreExpr   = '$point';
#$TimeExpr    = '$time_avg';
#$SpaceExpr   = '$space_max';
#$SampleInput = '0';
#$CardLimit   = '-1';

# C / CPP

#$IncPaths    = '';
#$CompileCmd  = '$CC $IncPaths $source';
#$LinkLibs    = '-lm';
#$ObjectList  = '$objects';
#$LinkCmd     = '$LD $LinkLibs $objects';
#$TestCmd     = './a.out < $test_in > $user_out';

$CompileCmd  = '$JAVAC -cp algs4.jar:stdlib.jar $source';
$ClassName   = 'Clustering';
$TestCmd     = '-cp algs4.jar:stdlib.jar:. $ClassName $test_in > $user_out';
$TimeLimit   = '25';
$Timeout     = '25';
$PointUnit   = '2';
$SoftLateDay = '7';
$SoftLatePenalty = '0.7';
$HardLatePenalty = '0.5';
