#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/mm.h>
#include <linux/proc_fs.h>
#include <linux/fs.h>
#include <linux/string.h>
#include <asm/uaccess.h>

//#define MODULE
#define USER_ROOT_DIR "pf"
#define MODULE1	"Pfcount"
#define MODULE2 "Jiffies"

extern unsigned long volatile pfcount;
extern unsigned long volatile jiffies;

struct proc_dir_entry *proc_pfcount;
struct proc_dir_entry *proc_jiffies;     
struct proc_dir_entry *my_dir;

static ssize_t read_pfcount(struct file *file, char __user *pszPage, size_t size, loff_t *off)
{
    int len = 0;
    len = sprintf(pszPage, "%d \n", pfcount);
    if (len <= *off)
    {
   	    return 0;
    }
   	len -= *off;
    *off += len;
    return len;
}

static ssize_t read_jiffies(struct file *file, char __user *pszPage, size_t size, loff_t *off)
{
    int len = 0;
    len = sprintf(pszPage, "%d \n", jiffies);
    if (len <= *off)
    {
   	    return 0;
    }
   	len -= *off;
    *off += len;
    return len;
}

static const struct file_operations proc_fops1=
{
	.owner  = THIS_MODULE,
 	.read   = read_pfcount,
};

static const struct file_operations proc_fops2=
{
	.owner  = THIS_MODULE,
 	.read   = read_jiffies,
};


int pf_init(void)
{
    printk("init_module()\n");
	my_dir = proc_mkdir(USER_ROOT_DIR, NULL);
    proc_pfcount = proc_create(MODULE1,0,my_dir,&proc_fops1);
    proc_jiffies = proc_create(MODULE2,0,my_dir,&proc_fops2);
    return 0;
}

void pf_exit(void)
{
    remove_proc_entry(MODULE1,my_dir);
    remove_proc_entry(MODULE2,my_dir);
    remove_proc_entry(USER_ROOT_DIR,NULL);
}

module_init(pf_init);
module_exit(pf_exit);
MODULE_LICENSE("GPL");

